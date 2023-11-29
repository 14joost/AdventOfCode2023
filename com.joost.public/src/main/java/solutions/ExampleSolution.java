package solutions;

import java.io.FileNotFoundException;

public class ExampleSolution extends Solution {
    public ExampleSolution(int day) {
        super(day);
    }

    @Override
    public void solve(boolean isExample) throws FileNotFoundException {
        var reader = this.getReader(isExample);
    }
}
