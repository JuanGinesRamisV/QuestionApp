package Data;

public class Question {
    private String question;
    private boolean answer;

    //<editor-fold desc="getters and setters">
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    //</editor-fold>


    public Question(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public static boolean comprobarRespuesta(Question question, boolean respuesta){
        if (question.isAnswer()==respuesta){
            return true;
        }else{
            return false;
        }
    }

}
