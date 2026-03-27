package kr.ac.kaist.wala.adlib.analysis.malicious;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by leesh on 07/04/2018.
 */
public class PatternMaker {
    public MaliciousPatternChecker.MaliciousPattern[] make(String name, MaliciousPatternChecker.MaliciousPoint[][] points) {
        List<Set<MaliciousPatternChecker.MaliciousPoint>> l = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            l.add(Arrays.stream(points[i]).collect(Collectors.toSet()));
        }

        Set<List<MaliciousPatternChecker.MaliciousPoint>> res = cartesianProduct(l);

        int index = 0;
        MaliciousPatternChecker.MaliciousPattern[] patterns = new MaliciousPatternChecker.MaliciousPattern[res.size()];

        for (List<MaliciousPatternChecker.MaliciousPoint> pl : res) {
            patterns[index] = new MaliciousPatternChecker.MaliciousPattern(name + (++index), pl.toArray(new MaliciousPatternChecker.MaliciousPoint[0]));
        }

        return patterns;
    }

    /** Same semantics as Guava {@code Sets.cartesianProduct}: order follows input sets and elements. */
    private static Set<List<MaliciousPatternChecker.MaliciousPoint>> cartesianProduct(
            List<Set<MaliciousPatternChecker.MaliciousPoint>> sets) {
        Set<List<MaliciousPatternChecker.MaliciousPoint>> out = new LinkedHashSet<>();
        cartesianProductRec(sets, 0, new ArrayList<>(), out);
        return out;
    }

    private static void cartesianProductRec(
            List<Set<MaliciousPatternChecker.MaliciousPoint>> sets,
            int depth,
            List<MaliciousPatternChecker.MaliciousPoint> prefix,
            Set<List<MaliciousPatternChecker.MaliciousPoint>> out) {
        if (depth == sets.size()) {
            out.add(new ArrayList<>(prefix));
            return;
        }
        for (MaliciousPatternChecker.MaliciousPoint p : sets.get(depth)) {
            prefix.add(p);
            cartesianProductRec(sets, depth + 1, prefix, out);
            prefix.remove(prefix.size() - 1);
        }
    }
}
