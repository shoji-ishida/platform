package mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import play.Play;
import uk.co.panaxiom.playjongo.PlayJongo;

/**
 * Created by ishida on 2016/09/27.
 */
public class Session {
    public static PlayJongo jongo = Play.application().injector().instanceOf(PlayJongo.class);

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

}
