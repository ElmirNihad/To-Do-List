public class Task {
    private String description;
    private boolean done;
    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    public String getDescription() {
        return description;
    }

    public boolean isTamamlandi() {
        return done;
    }

    public void tamamla() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (done ? "[✓] " : "[ ] ") + description;
    }


}
