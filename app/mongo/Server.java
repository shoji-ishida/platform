package mongo;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import play.Play;
import uk.co.panaxiom.playjongo.PlayJongo;

import java.util.List;

/**
 * Created by ishida on 2016/09/27.
 */
public class Server {
    //public static PlayJongo jongo = Play.application().injector().instanceOf(PlayJongo.class);
    public static PlayJongo jongo = play.api.Play.current().injector().instanceOf(PlayJongo.class);

    public static MongoCollection servers() {
        return jongo.getCollection("servers");
    }

    @JsonProperty("_id")
    public ObjectId id;

    public String hostname;
    public String scheme;
    public int port;
    public String path_prefix;
    public String host;


    public static Server findByPort(int port) {
        return servers().findOne("{port: #}", port).as(Server.class);
    }

    public static List<ObjectId> getNodes() {
        return servers().distinct("_id").as(ObjectId.class);
    }

    public static MongoCursor<Server> getServers() {
        return servers().find().as(Server.class);
    }
}
