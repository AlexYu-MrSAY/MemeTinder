package com.company.syugai;

import com.company.syugai.configuration.DatabaseConfiguration;
import com.company.syugai.configuration.JdbcDatabaseConfiguration;
import com.company.syugai.controllers.*;
import com.company.syugai.serealization_deserealization.UserDeserialization;
import com.company.syugai.model.*;
import com.company.syugai.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.Javalin;
import io.javalin.core.security.Role;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.core.security.SecurityUtil.roles;

public class Main {
    public static Role getRole(Context context, Service<User, Integer> userService) {
        if (context.basicAuthCredentialsExist()) {
            String login = context.basicAuthCredentials().getUsername();
            String password = context.basicAuthCredentials().getPassword();
            User actor = userService.findByColumnUnique("login", login);
            if (BCrypt.checkpw(password, actor.getPassword())) {
                return actor.getRole();
            } else {
                throw new UnauthorizedResponse();
            }
        } else {
            throw new UnauthorizedResponse();
        }
    }
    public static void main(String[] args) throws SQLException {
        DatabaseConfiguration databaseConfiguration = new JdbcDatabaseConfiguration("jdbc:sqlite:C:\\Users\\yugai\\OneDrive\\Документы\\MemeTinder.db");
        Service<User, Integer> userService = new UserService(DaoManager.createDao(databaseConfiguration.source(), User.class));
        Service<Meme, Integer> memeService = new MemeService(DaoManager.createDao(databaseConfiguration.source(), Meme.class));
        Service<MemeReview, Integer> memeReviewService = new MemeReviewService(DaoManager.createDao(databaseConfiguration.source(), MemeReview.class));
        Service<UserInteraction, Integer> userInteractionService = new UserInteractionService(DaoManager.createDao(databaseConfiguration.source(), UserInteraction.class));

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(
                        new SimpleModule()
                                .addDeserializer(User.class, new UserDeserialization())
                );

        Controllers<User, Integer> userController = new UserController(userService, objectMapper);
        Controllers<Meme, Integer> memeController = new MemeController(memeService, objectMapper, userService);
        Controllers<MemeReview, Integer> memeReviewController = new MemeReviewController(memeReviewService, objectMapper, userService, memeService);
        Controllers<UserInteraction, Integer> userInteractionController = new UserInteractionController(userInteractionService, objectMapper, userService);

        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.enableCorsForAllOrigins();
            javalinConfig.defaultContentType = "application/json";
            javalinConfig.prefer405over404 = true;
            javalinConfig.accessManager((handler, context, set) -> {
                Role userRole = getRole(context, userService);
                if (set.contains(userRole)) {
                    handler.handle(context);
                } else {
                    throw new ForbiddenResponse();
                }
            });
        });

        app.routes(() -> {
            path("users", () -> {
                get(userController::getAll, roles(UserRole.ADMIN));
                post(userController::postOne);
                path(":id", () -> {
                    get((ctx) -> userController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> userController.patch(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    delete((ctx) -> userController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                });
            });

            path("memes", () -> {
                get(memeController::getAll);
                post(memeController::postOne);
                path(":id", () -> {
                    get((ctx) -> memeController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> memeController.patch(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                    delete((ctx) -> memeController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                });
            });

            path("memeReviews", () -> {
                get(memeReviewController::getAll);
                path(":userId/:memeId", () -> post(memeReviewController::postOne));
                path(":id", () -> {
                    get((ctx) -> memeReviewController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> memeReviewController.patch(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                    delete((ctx) -> memeReviewController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                });
            });

            path("userInteractions", () -> {
                get(userInteractionController::getAll);
                path(":sourceId/:targetId", () -> post(userInteractionController::postOne));
                path(":id", () -> {
                    get((ctx) -> userInteractionController.getOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON, UserRole.ADMIN));
                    patch((ctx) -> userInteractionController.patch(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                    delete((ctx) -> userInteractionController.deleteOne(ctx, ctx.pathParam("id", Integer.class).get()), roles(UserRole.COMMON));
                });
            });
        });
        app.start(8080);
    }
}

