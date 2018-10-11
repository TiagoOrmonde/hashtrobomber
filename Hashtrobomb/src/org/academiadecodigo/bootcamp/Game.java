package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Objects.GameObject;
import org.academiadecodigo.bootcamp.Objects.ObjectFactory;
import org.academiadecodigo.bootcamp.Objects.Player;
import org.academiadecodigo.bootcamp.Position.Directions;
import org.academiadecodigo.bootcamp.Position.Position;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.LinkedList;
import java.util.List;

public class Game implements KeyboardHandler {
    private Field field;
    private Level level;
    private Player[] players = new Player[2];
    private Keyboard keyboard = new Keyboard(this);
    public static List<GameObject> gameObjects = new LinkedList<>();

    public Game() {
        init();
    }

    public void init() {

        field = new Field();
        level = new Level(field);
        gameObjects = level.level2();
        players[0] = ObjectFactory.createPlayer("Player 1", 0, 0, field, 0);
        players[1] = ObjectFactory.createPlayer("Player 2", 14, 14, field, 1);
        keyBinding();
    }

    public void start() {

    }

    @Override
    public void keyPressed(KeyboardEvent e) {

        int currentCol_p1 = players[0].getPosition().getCol();
        int currentRow_p1 = players[0].getPosition().getRow();
        int currentCol_p2 = players[1].getPosition().getCol();
        int currentRow_p2 = players[1].getPosition().getRow();


        switch (e.getKey()) {
            case KeyboardEvent.KEY_W:
                if (players[0].isMovementAvailable(new Position(currentCol_p1, currentRow_p1 - 1, field))) {
                    players[0].move(Directions.UP);
                }
                break;
            case KeyboardEvent.KEY_S:
                if (players[0].isMovementAvailable(new Position(currentCol_p1, currentRow_p1 + 1, field))) {
                    players[0].move(Directions.DOWN);
                }
                break;
            case KeyboardEvent.KEY_A:
                if (players[0].isMovementAvailable(new Position(currentCol_p1 - 1, currentRow_p1, field))) {
                    players[0].move(Directions.LEFT);
                }
                break;
            case KeyboardEvent.KEY_D:
                if (players[0].isMovementAvailable(new Position(currentCol_p1 + 1, currentRow_p1, field))) {
                    players[0].move(Directions.RIGHT);
                }
                break;
            case KeyboardEvent.KEY_SPACE:
                if (!CollisionDetector.checkCollision(players[0].getPosition()) && players[0].dropBomb()) {
                    gameObjects.add(ObjectFactory.createBomb(currentCol_p1, currentRow_p1, players[0], players[0].getBombPower(), field));
                }
                break;

            case KeyboardEvent.KEY_UP:
                if (players[1].isMovementAvailable(new Position(currentCol_p2, currentRow_p2 - 1, field))) {
                    players[1].move(Directions.UP);
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (players[1].isMovementAvailable(new Position(currentCol_p2, currentRow_p2 + 1, field))) {
                    players[1].move(Directions.DOWN);
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (players[1].isMovementAvailable(new Position(currentCol_p2 - 1, currentRow_p2, field))) {
                    players[1].move(Directions.LEFT);
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (players[1].isMovementAvailable(new Position(currentCol_p2 + 1, currentRow_p2, field))) {

                    players[1].move(Directions.RIGHT);
                }
                break;
            case KeyboardEvent.KEY_L:
                if (!CollisionDetector.checkCollision(players[1].getPosition()) && players[1].dropBomb()) {

                    gameObjects.add(ObjectFactory.createBomb(currentCol_p2, currentRow_p2, players[1], players[1].getBombPower(), field));
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void keyBinding() {
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(space);

        KeyboardEvent up1 = new KeyboardEvent();
        up1.setKey(KeyboardEvent.KEY_UP);
        up1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up1);

        KeyboardEvent down1 = new KeyboardEvent();
        down1.setKey(KeyboardEvent.KEY_DOWN);
        down1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down1);

        KeyboardEvent left1 = new KeyboardEvent();
        left1.setKey(KeyboardEvent.KEY_LEFT);
        left1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left1);

        KeyboardEvent right1 = new KeyboardEvent();
        right1.setKey(KeyboardEvent.KEY_RIGHT);
        right1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right1);

        KeyboardEvent l = new KeyboardEvent();
        l.setKey(KeyboardEvent.KEY_L);
        l.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(l);
    }

}
