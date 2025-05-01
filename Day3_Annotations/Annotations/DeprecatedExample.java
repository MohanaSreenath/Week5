package Annotations;
class LegacyAPI {
    @Deprecated
    void oldFeature() {
        System.out.println("This is an old feature, use newFeature instead.");
    }

    void newFeature() {
        System.out.println("This is the recommended new feature.");
    }
}

public class DeprecatedExample {
    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature(); 
        api.newFeature();
    }
}