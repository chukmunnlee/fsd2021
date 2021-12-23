package sdf.extra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import sdf.extra.Constants.CodePeg;

public class CodeRow implements Row {

    private List<CodePeg> code = Collections.emptyList();

    @Override
    public Optional<List<CodePeg>> getPegs() {
        if (0 == code.size())
            return Optional.empty();
        return Optional.of(Collections.unmodifiableList(code));
    }

    @Override
    public void setPegs(CodePeg p0, CodePeg p1, CodePeg p2, CodePeg p3) {
        code = new ArrayList<>();
        code.add(p0);
        code.add(p1);
        code.add(p2);
        code.add(p3);
    }

    @Override
    public String toString() {
        return "code: %s".formatted(code.toString());
    }
}
