package org.opendevup.services;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class BourseGatewayRestService {
	@Autowired
	private RestTemplate restTemplate;
	private List<Societe> result=new ArrayList<Societe>();
	@RequestMapping(value="/names")
	public Societe listdesSocietes(){

		
		ParameterizedTypeReference<Resource<Societe>> responseType=new ParameterizedTypeReference<Resource<Societe>>() {};
		//probleme avec restTemplate à voir : pas de retour de liste ou collection !
		return restTemplate.exchange("http://societe-service/societes/1",HttpMethod.GET, null, responseType).getBody().getContent();
	
	} 

}

class Societe{
	private Long Id;
	private String nomSociete;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
}



@Configuration
class MyConfiguration {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}