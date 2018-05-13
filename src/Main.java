package barray.joi;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * Main.java
 *
 * Handles high-level game logic, including game state and handling time
 * dependant loops.
 **/
public class Main extends Application{
  private static final boolean obj = true;
  private static final boolean filled = true;

  /**
   **/
  public Parent createContent() throws Exception{
    /* Import STL model */
    MeshView example;
    if(obj){
      example = Loader.loadObj("assets/models/computer.obj");
    }else{
      example = Loader.loadStl("assets/models/computer.stl");
    }
    if(filled){
      PhongMaterial pm = new PhongMaterial();
      pm.setDiffuseMap(new Image((new File("assets/diffuse/computer.png").toURI().toString())));
      pm.setSpecularColor(Color.WHITE);
      example.setMaterial(pm);
    }else{
      example.setMaterial(new PhongMaterial(Color.RED));
      example.setDrawMode(DrawMode.LINE);
    }
    /* Position the camera in the scene */
    PerspectiveCamera camera = new PerspectiveCamera(true);
    if(obj){
      camera.getTransforms().addAll(
        new Rotate(-5, Rotate.Y_AXIS),
        new Rotate(-110 + 270, Rotate.X_AXIS),
        new Translate(0, 0, -80)
      );
    }else{
      camera.getTransforms().addAll(
        new Rotate(5, Rotate.Y_AXIS),
        new Rotate(-110, Rotate.X_AXIS),
        new Translate(0, 0, -80)
      );
    }
    /* Build the Scene Graph */
    Group root = new Group();
    root.getChildren().add(camera);
    root.getChildren().add(example);
    /* Use a SubScene */
    SubScene subScene = new SubScene(root, 500, 500);
    subScene.setFill(Color.ALICEBLUE);
    subScene.setCamera(camera);
    Group group = new Group();
    group.getChildren().add(subScene);
    return group;
  }

  @Override
  public void start(Stage primaryStage) throws Exception{
    primaryStage.setResizable(false);
    Scene scene = new Scene(createContent());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * main()
   *
   * Pass along the command line arguments to an instance of the Main class.
   *
   * @param args The command line arguments.
   **/
  public static void main(String[] args){
    /* TODO: Make decision about parsing command line arguments. */
    launch(args);
  }
}
