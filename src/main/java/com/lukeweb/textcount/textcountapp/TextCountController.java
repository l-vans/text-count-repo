package com.lukeweb.textcount.textcountapp;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TextCountController {

	@Autowired
	private TextCountService textCountService;
	private ArrayList<String> fWords;
	private int count = 0;
	private String avLength = "";
	private String frequencies = "";
	
	@RequestMapping(value="/counts", method=RequestMethod.POST,  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Text filtertext(@RequestParam("file") MultipartFile file) throws IOException {
		Text t = new Text();
		fWords = textCountService.filterText(file);	
		this.getCount(fWords);
		this.getAverageLength(fWords, count);
		this.numberOfIndyWords(fWords);
		t.setCount("Word count = " + String.valueOf(count));
		t.setAverageLength(avLength);
		t.setFinalFreqs(frequencies);
		return t;	
	}
	
	public void getCount(ArrayList<String> fWords) {
      		count = textCountService.getCount(fWords);
	}
	
	public void getAverageLength(ArrayList<String> fWords, int c) {
		avLength = textCountService.getAverageLength(fWords, c);
	}
	
	public void numberOfIndyWords(ArrayList<String> fWords) {
		frequencies = textCountService.numberOfIndyWords(fWords);
	}
	

	
	
	
	
	
	
	
	
	

	
	
}
