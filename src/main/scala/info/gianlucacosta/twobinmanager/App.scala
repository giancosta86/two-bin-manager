package info.gianlucacosta.twobinmanager

import java.io.File
import java.util.concurrent.atomic.AtomicReference
import javafx.stage.Stage

import info.gianlucacosta.helios.apps.{AppInfo, AuroraAppInfo}
import info.gianlucacosta.helios.desktop.DesktopUtils
import info.gianlucacosta.helios.fx.application.{AppBase, SplashStage}
import info.gianlucacosta.twobinmanager.main.MainScene
import info.gianlucacosta.twobinmanager.plugins.{PluginsDirectoryWatcher, PluginsRegistry}
import info.gianlucacosta.twobinpack.icons.MainIcon
import info.gianlucacosta.twobinpack.twobinmanager.ArtifactInfo

import scalafx.Includes._
import scalafx.application.Platform
import scalafx.stage.WindowEvent


object App {
  private val MajorVersion =
    ArtifactInfo.version.split('.').head

  val Directory: File =
    new File(
      DesktopUtils.homeDirectory.get,
      s".${ArtifactInfo.name}${MajorVersion}"
    )


  val PluginsDirectory: File =
    new File(
      Directory,
      "plugins"
    )


  private val pluginsRegistry: AtomicReference[PluginsRegistry] =
    new AtomicReference[PluginsRegistry](null)

  def PluginsRegistry: PluginsRegistry =
    pluginsRegistry.get()


  def PluginsRegistry_=(newValue: PluginsRegistry) = {
    pluginsRegistry.set(newValue)
  }
}

/**
  * Program's entry point
  */
class App extends AppBase(AuroraAppInfo(ArtifactInfo, MainIcon)) {
  override def startup(appInfo: AppInfo, splashStage: SplashStage, primaryStage: Stage): Unit = {
    App.Directory.mkdirs()

    AppDb.DbConnection.open()


    App.PluginsDirectory.mkdirs()

    App.PluginsRegistry =
      new PluginsRegistry(
        Some(App.PluginsDirectory)
      )

    new PluginsDirectoryWatcher(App.PluginsDirectory.toPath) {
      start()
    }

    val scene =
      new MainScene(appInfo, primaryStage)

    Platform.runLater {
      primaryStage.resizable =
        false

      primaryStage.setScene(scene)

      primaryStage.title =
        appInfo.name

      primaryStage.onShown =
        (event: WindowEvent) => {
          primaryStage.sizeToScene()
          primaryStage.centerOnScreen()
        }
    }
  }

  override def stop(): Unit = {
    AppDb.DbConnection.close()
  }
}