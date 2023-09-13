package qa.dataread;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Environment {

	private Set<Parameter> param = new HashSet<>();

	private String name;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	@XmlElement(name = "Parameter", type = Parameter.class)
	public Set<Parameter> getParam() {
		return param;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParam(Set<Parameter> param) {
		this.param = param;
	}

}
