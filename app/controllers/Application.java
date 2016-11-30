package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.databind.JsonNode;
import mongo.Server;
import mongo.Session;
import org.jongo.MongoCursor;
import play.libs.Akka;
import play.libs.F.Promise;
import play.mvc.*;

import scala.concurrent.ExecutionContext;
import views.html.*;

import java.util.*;

import static play.libs.F.Promise.promise;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Promise<Result> accesspoint() {
        // Parse request JSON body
        JsonNode json = request().body().asJson();
        String access_token = json.get("access_token").asText();
        String access_source = json.get("access_source").asText();

        ExecutionContext jdbcExecutionContext = Akka.system().dispatchers()
                .lookup("jdbc-execution-context");

        return Promise.promise(() -> {

        // retrieve application_id from access_token
        String sql = "select application_id from application_tokens where access_token = :accesstoken";
        SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
        sqlQuery.setParameter("accesstoken", access_token);
        List<SqlRow> list = sqlQuery.findList();
        SqlRow sqlRow = list.get(0);

        Map<Server, Long> counts = new HashMap<>();

        // Retrieve a list of websocket servers
        MongoCursor<Server> servers = Server.getServers();
        while (servers.hasNext()) {
            Server server = servers.next();
            long count = Session.getCount(server.id.toString());
            counts.put(server, count);
        }

        // List for sorting
        List<Map.Entry<Server, Long>> entries = new ArrayList<Map.Entry<Server, Long>>(counts.entrySet());

        Comparator<Map.Entry<Server, Long>> comp = (Map.Entry<Server, Long> entry1, Map.Entry<Server, Long> entry2) ->
                (int) (entry1.getValue()).compareTo(entry2.getValue());
        entries.sort(comp);

        Server target = (Server) entries.get(0).getKey();
        String uri = "wss://" + target.hostname + target.path_prefix + "/" + sqlRow.getString("application_id");
            return uri;
    }, jdbcExecutionContext)
        .map((String uri) -> ok(uri));
    }

    public Result accesspointList() {
        return Results.TODO;
    }
}
