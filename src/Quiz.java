public class Quiz {
    
    // brokenMirror("James");
    public String brokenMirror(String str){
        String result = "";
        for(int i = str.length() - 1; i > -1; i-=2){
            result = result + str.substring(i, i+1);
        }
        return result;
    }
}
