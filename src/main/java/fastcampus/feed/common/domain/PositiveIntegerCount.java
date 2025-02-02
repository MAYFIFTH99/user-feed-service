package fastcampus.feed.common.domain;

public class PositiveIntegerCount {
    private int count;

    public PositiveIntegerCount() {
        count = 0;
    }

    public void increase(){
        count++;
    }

    public void decrease(){
        if(count - 1 < 0){
            return;
        }
        count--;
    }

    public int getCount(){
        return count;
    }

    public PositiveIntegerCount(int count) {
        this.count = count;
        if(count < 0){
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }

}
