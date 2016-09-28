package mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import play.Play;
import uk.co.panaxiom.playjongo.PlayJongo;

/**
 * Created by ishida on 2016/09/27.
 */
public class Session {
    public static PlayJongo jongo = play.api.Play.current().injector().instanceOf(PlayJongo.class);

    public static MongoCollection sessions() {
        return jongo.getCollection("sessions");
    }

    @JsonProperty("_id")
    public ObjectId id;

    public String node_id;
    public String application_id;
    public String type;
    public String created_at;
    public String updated_at;
    public String quasar_id;
    public String user_agent;

    public static MongoCursor<Session> getSessions() {
        return sessions().find().as(Session.class);
    }
    
    public static long getCount(String nodeId) {
        long count = sessions().count("{node_id: #, type: 'websocket'}", nodeId);
        return count;
    }
}
