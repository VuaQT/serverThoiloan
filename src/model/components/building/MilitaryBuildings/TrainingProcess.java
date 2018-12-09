package model.components.building.MilitaryBuildings;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class TrainingProcess {
    public boolean isTraining=false;
    public int trainingMoment;
    public Queue<TrainingElement> queue = new LinkedList<TrainingElement>();

    public TrainingProcess() {
    }
}
