package cpsc2150.connectX;

public interface IGameBoardFactory {
    IGameBoard makeStack(int r, int c, int r2w);
}

class MFactory implements IGameBoardFactory {
    public IGameBoard makeStack(int r, int c, int r2w) {
        return new GameBoardMem(r,c,r2w);
    }
}

class FFactory implements IGameBoardFactory {
    public IGameBoard makeStack(int r, int c, int r2w) {
        return new GameBoard(r, c, r2w);
    }
}