package contextHolder;

/**
 * @author yang
 */
public class DataSourceContextHolder {

    public DataSourceContextHolder() {
    }

    private static final ThreadLocal<String> contextHolder= new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "cpprd";
        }
    };

    public static void setContextHolder(String serverName){contextHolder.set(serverName);}
    public static String getContextHolder(){return contextHolder.get();}
    public static void clearContextHolder(){contextHolder.remove();}
}
