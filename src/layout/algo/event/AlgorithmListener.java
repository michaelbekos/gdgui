package layout.algo.event;

/**
 * This is an listener interface for an algorithm, support three methods:
 * -  algorithmStarted
 * -  algorithmFinished
 * -  algorithmStateChanged
 * @author Michael A. Bekos
 */
public interface AlgorithmListener
{
    /**
     * Algorithm Started
     */
    void algorithmStarted(AlgorithmEvent evt);

    /**
     * Algorithm Finished
     */
    void algorithmFinished(AlgorithmEvent evt);

    /**
     * Returns the current status of the algorithm.
     */
    void algorithmStateChanged(AlgorithmEvent evt);
}
