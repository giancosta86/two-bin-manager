package info.gianlucacosta.twobinmanager.plugins

import java.nio.file.{Path, WatchEvent}
import java.time.Duration

import info.gianlucacosta.helios.files.DirectoryWatcher
import info.gianlucacosta.twobinmanager.App

/**
  * Monitors the plugins directory, reloading the plugins registry when a change occurs
  *
  * @param pluginsDirectory The directory containing the JAR plugin files
  */
class PluginsDirectoryWatcher(pluginsDirectory: Path) extends DirectoryWatcher(
  pluginsDirectory,
  Duration.ofSeconds(7)) {
  override def onEvents(events: List[WatchEvent[_]]): Unit = {
    App.PluginsRegistry =
      new PluginsRegistry(
        Some(pluginsDirectory.toFile)
      )
  }
}