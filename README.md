# Java Selenium Flexible WebDriver Hooks

Offers a straight forward xml abstraction layer for the registration of Selenium "WebDriverEventListener" event listener for more flexibility.

The project consists of the actual source code for the loading of event listener from xml, an example on how to use it and a gradle file, which can be used to
import the code as an project into any gradle supporting IDE (IntelliJ, Eclipse, ...).

Benefits:
1. Event listener for different environments by loading different xml files
2. Adding/Removing event listener without code changes
3. Selenium event listener allow you to do specific stuff at a specific time like
    1. Documenting actions
    2. Checking for JS errors
    3. Removing annoying stuff like sticky toolbars

## Usage

The code to load the event listener is located in the "src/test/java/hooks" folder. Simply copy the content to your own project and use it however you like.
All you need to do is call the "Hooks.registerHooks(seleniumWebDriverObject, "pathToHooks.xml");" method.

### Dependencies

In order to use this feature you have to add the following dependencies to your project:

1. [Selenium server](http://docs.seleniumhq.org/download/) (tested with version 3.3.1)
2. [Simple XML](http://simple.sourceforge.net/) (tested with version 2.7.1)

The version of the dependencies should not influence this projects behaviour.
Look up the gradle file to see the gradle dependencies declarations.

### Structure of the xml file

```xml
<root>
  <Hooks class="java.util.ArrayList">
        <hook HookPath="example.hooks.CheckForJavascriptErrorsHook"/>
        <hook HookPath="example.hooks.WebDriverActionProtocolHook"/>
  </Hooks>
</root>
```

The root node "root" contains the node "Hooks" under which the list of hooks have to be placed. Each hook is one element and each element contains the attribute "HookPath".
The "HookPath" attribute must point to the qualified name of a class, which implements the Selenium "WebDriverEventListener" interface.

## Example Project

This little example project registers two event listener by using the xml mechanism, visits the Bing search page, searches for the term "test" and opens the first search result.
The project can be found in the "test" source folder in the "example" package. It consists of the following elements:

1. "hooks": This package contains two example event listener.
    1. "CheckForJavascriptErrorsHook": This hook injects some javascript into the webpage and checks after each action if a javascript error occurred.
    2. "WebDriverActionProtocolHook": This hook prints actions like button clicks in the output terminal.
2. "pages": This package contains a simple page object which represents the the Bing search page.
3. "tests": This package contains the "Test" class which executes the test with the hooks from 1.