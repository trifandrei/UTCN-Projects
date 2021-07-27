package NLP;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Lemma {
private	Map<String,String> map=new HashMap<String,String>();  

	public Lemma(String text) {
		StanfordCoreNLP stan = Pipeline.getPipeline();
		
		CoreDocument doc = new CoreDocument(text);
		
		stan.annotate(doc);
		
		List<CoreLabel> list = doc.tokens();
		
		for (CoreLabel core : list) {
			
			this.map.put(core.originalText(), core.lemma());
		}
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	
}
