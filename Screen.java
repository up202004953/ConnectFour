package ConnectFour;

public class Screen {
    public static void clear() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (isWindows(os)) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (isUnix(os) || isMac(os)){
                Runtime.getRuntime().exec("clear");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean isWindows(String OS) {
        return (OS.contains("win"));
    }

    public static boolean isMac(String OS) {
        return (OS.contains("mac"));
    }

    public static boolean isUnix(String OS) {
        return (OS.contains("nix")
                || OS.contains("nux")
                || OS.contains("aix"));
    }
}