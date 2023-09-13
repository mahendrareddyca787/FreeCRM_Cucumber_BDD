package qa.dataread;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "key", "value" })
public class Parameter {

	private String key;

	private String value;

	public Parameter() {
	}

	@XmlAttribute(name = "key")
	public String getKey() {
		return key;
	}

	@XmlAttribute(name = "value")
	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
