package trivia.domain;

import java.util.LinkedList;

public class BoardFactory {
    public LinkedList<Cell> getCells(int nbCells)
    {
        int i=0;
        Cell currentCell,previousCell;
        LinkedList<Cell> cells = new LinkedList<>();

        do {
            //Determine the category of question Type based on index i and category available.

            //currCell = new Cell(qt,prevCell);
            //prevCell = currCell;
            i++;
        }while(i<nbCells);


        //cells.getFirst().setPreviousCell(currCell);




        return cells;
    }
}
