package no.vannebo.caroline.bookregistry;

public enum Genre {
    ACTION, ROMANCE, CRIME, TERROR, DRAMA, SCI_FI, FANTASY, CHILDREN, NONFICTION, FAIRYTALE, POLITICAL, CLASSIC, OTHER;

    @Override
    public String toString() {
        return this.name();
    }
}