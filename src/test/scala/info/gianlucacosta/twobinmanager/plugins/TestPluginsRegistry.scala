package info.gianlucacosta.twobinmanager.plugins

import org.scalatest.{FlatSpec, Matchers}

class TestPluginsRegistry extends FlatSpec with Matchers {
  "The bundled analytics dimensions" should "NOT be empty" in {
    val pluginsRegistry =
      new PluginsRegistry(None)

    pluginsRegistry.analyticsDimensions should not be empty
  }


  "The bundled problem generators" should "NOT be empty" in {
    val pluginsRegistry =
      new PluginsRegistry(None)

    pluginsRegistry.problemGenerators should not be empty
  }


  "The bundled importers" should "NOT be empty" in {
    val pluginsRegistry =
      new PluginsRegistry(None)

    pluginsRegistry.importers should not be empty
  }
}
