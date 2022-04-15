public class Screen {
    public static void clear() {
        try{
            String os = System.getProperty("os.name"); //Check the current operating system

            if(os.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process process = pb.inheritIO().start();
                process.waitFor();
            }
            else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process process = pb.inheritIO().start();

                process.waitFor();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}