package hooks.xml;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Root structure for hook declarations.
 **/
@Root
public class HookDeclarations {

    /**
     * List of hook declarations.
     **/
    @ElementList(name = "Hooks")
    private List<HookDeclaration> hooks;

    /**
     * Returns the list of hook declarations.
     **/
    public List<HookDeclaration> getHooks() {
        return hooks;
    }
}
