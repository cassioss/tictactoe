package learner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cassio dos Santos Sousa
 * @version 1.0
 */
public class BasicLearner {

    private List<Integer> _concatenate(List<Integer> biggerList, List<Integer> smallerByOne) {
        List<Integer> concatenated = new ArrayList<>();
        for (int i = 0; i < smallerByOne.size(); i++) {
            concatenated.add(biggerList.get(i));
            concatenated.add(smallerByOne.get(i));
        }
        if (biggerList.size() > smallerByOne.size())
            concatenated.add(biggerList.get(smallerByOne.size()));
        return concatenated;
    }

    private List<Integer> concatenate(List<Integer> firstList, List<Integer> secondList) {
        if (firstList.size() >= secondList.size())
            return _concatenate(firstList, secondList);
        else
            return _concatenate(secondList, firstList);
    }

}
