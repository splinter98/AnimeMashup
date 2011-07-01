package animegwt.client.data;

import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.ScriptTagProxy;

public abstract class GenericData<D> {
	final String url_prefix; 
	ScriptTagProxy<D> proxy;
	final BaseLoader<D> loader;
	
	@SuppressWarnings("unchecked")
	public GenericData(String url_prefix, BaseLoader<D> loader) {
		this.url_prefix = url_prefix;
		this.proxy = (ScriptTagProxy<D>) loader.getProxy();
		this.loader = loader;
	}

	public BaseLoader<D> getLoader() {
		return loader;
	}
	
	public void get(String value) {
	    proxy.setUrl(url_prefix+value);  
	    loader.load();
	} 
	
}
