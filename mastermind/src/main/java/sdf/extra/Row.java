package sdf.extra;

import java.util.List;
import java.util.Optional;

import sdf.extra.Constants.CodePeg;

public interface Row {
    public Optional<List<CodePeg>> getPegs();
    public void setPegs(CodePeg p0, CodePeg p1, CodePeg p2, CodePeg p3);
}
