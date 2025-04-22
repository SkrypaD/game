public class Vector2 {
    private int x;
    private int y;

    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 other){
        this.x = other.x;
        this.y = other.y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }

    @Override
    public String toString(){
        return "Vector2 [x=" + x + ", y=" + y + "]";
    }
}

