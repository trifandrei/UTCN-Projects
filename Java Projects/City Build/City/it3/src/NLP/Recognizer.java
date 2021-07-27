package NLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Recognizer {
	List<String> verb = new ArrayList<String>();
	List<String> noun = new ArrayList<String>();

	public Recognizer(String text) {
		StanfordCoreNLP stan = Pipeline.getPipeline();
		CoreDocument doc = new CoreDocument(text);
		stan.annotate(doc);
		List<CoreLabel> list = doc.tokens();
		for (CoreLabel core : list) {
			makeList(core, text);

		}
	}

	private void makeList(CoreLabel core, String text) {
		String pos = core.get(CoreAnnotations.PartOfSpeechAnnotation.class);
		Lemma l = new Lemma(text);
		Map<String, String> map = l.getMap();

		if ((map.keySet().contains(core.originalText())) && (pos.toString().equals("VB") || (pos.equals("VBD")
				|| pos.equals("VBG") || pos.equals("VBN") || pos.equals("VBP") || pos.equals("VBZ")))) {
			this.verb.add(map.get(core.originalText()));
		}
		if (map.get(core.originalText()).equals(core.originalText()) && pos.equals("NN")
				|| (pos.equals("NNS") || pos.equals("NNP") || pos.equals("NNPS"))) {
			this.noun.add(map.get(core.originalText()));

		}
	}

	public List<String> getVerb() {
		return verb;
	}

	public void setVerb(List<String> verb) {
		this.verb = verb;
	}

	public List<String> getNoun() {
		return noun;
	}

	public void setNoun(List<String> noun) {
		this.noun = noun;
	}

}