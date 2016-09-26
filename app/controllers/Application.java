package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.databind.JsonNode;
import models.ApplicationTokens;
import play.*;
import play.mvc.*;

import views.html.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result accesspoint() {
        JsonNode json = request().body().asJson();
        String access_token = json.get("access_token").asText();
        String access_source = json.get("access_source").asText();

        //Duration deltaTime = Duration.ZERO;
        //Instant beginTime = Instant.now();

        String sql = "select application_id from application_tokens where access_token = :accesstoken";
        SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
        sqlQuery.setParameter("accesstoken", access_token);
        List<SqlRow> list = sqlQuery.findList();
        SqlRow sqlRow = list.get(0);

        //deltaTime = Duration.between(beginTime, Instant.now());
        //System.out.print(deltaTime+", ");
        //System.out.println(sqlRow.getString("application_id"));

        return ok(sqlRow.getString("application_id"));
    }

    public static Result accesspointList() {
        return Results.TODO;
    }
}
