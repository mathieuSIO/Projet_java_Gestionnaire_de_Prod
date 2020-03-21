package fr.miage.lcl.outil;

import java.util.List;
import java.util.Map;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class ConverterProperty {

	public static String stringPropertyToString(StringProperty sp) {
		return sp.get();
	}
	
	public static StringProperty stringToStringProperty(String s) {
		StringProperty sp = new SimpleStringProperty(s);
		return sp;
	}
	
	public static Integer integerPropertyToInteger(IntegerProperty ip) {
		return ip.get();
	}

	public static IntegerProperty integerToIntegerProperty(Integer i) {
		IntegerProperty ip = new SimpleIntegerProperty(i);
		return ip;
	}

	public static MapProperty mapToMapProperty(Map m) {
		MapProperty map = new SimpleMapProperty(FXCollections.observableHashMap());
		map.putAll(m);
		return map;
	}

	public static ListProperty listToListProperty(List l) {
		ListProperty liste = new SimpleListProperty(FXCollections.observableArrayList());
		liste.addAll(l);
		return liste;
	}

	public static Float floatPropertyToFloat(FloatProperty fp) {
		return fp.get();
	}

	public static FloatProperty floatToFloatProperty(Float f) {
		FloatProperty fp = new SimpleFloatProperty(f);
		return fp;
	}
}
