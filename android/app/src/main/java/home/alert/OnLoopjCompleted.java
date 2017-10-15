package home.alert;

public interface OnLoopjCompleted {
    public void taskStart();
    public void taskSuccess(String results);
    public void taskFailed();
    public void taskCompleted();
}
