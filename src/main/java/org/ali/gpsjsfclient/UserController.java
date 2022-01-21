package org.ali.gpsjsfclient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.NamedEvent;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import beans.Position;
import beans.Smartphone;
import beans.User;
import positionEJB.PositionLocal;
import smartphoneEJB.SmartphoneLocal;
import userEJB.UserLocal;

@ManagedBean(name = "userController", eager = true)
@SessionScoped
@NamedEvent
public class UserController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> options;
	private List<User> allUsers;
	private String selectedOption;
	private String option;
	private MapModel simpleModel;
	private List<Smartphone> phones;
	public List<Position> allPositions;
	private String userEmail;
	@EJB
	private UserLocal userLocal;
	@EJB
	public SmartphoneLocal smartPhoneLocal;
	@EJB
	public PositionLocal positionLocal;

	@PostConstruct
	public void init() {
		
		allUsers = userLocal.findAll();
		this.allPositions = positionLocal.findAll();
		options = new ArrayList<>();
		for(User u : userLocal.findAll()) {
			options.add(u.getNom());
		}
		
		 //phones = userLocal.findByEmail(option).getSmartphones();
			
	}
	public void onUserChange () {
		this.simpleModel = new DefaultMapModel();
		for (User u : allUsers) {
			if (u.getEmail().equals(userEmail)) {
				for (Smartphone sp : u.getSmartphones()) {
					for (Position p : sp.getPosition()) {
						this.simpleModel.addOverlay(new Marker(new LatLng(p.getLatitude(), p.getLongitude()), ""));
					}
				}
			}
		}
	}

	public void onSubmit() {
		System.out.println("this is the chosen user from submit : "+selectedOption);
	}
	public void change() {
		System.out.println("this is the chosen user : "+selectedOption);
	}
		

	public List<String> getOptions() {
		return options;
	}

	public String getUserEmail() {
		return userEmail;
	}




	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public void setOptions(List<String> options) {
		this.options = options;
	}



	public String getSelectedOption() {
		return selectedOption;
	}


	public MapModel getSimpleModel() {
		return simpleModel;
	}
	public void initMap() {
		simpleModel = new DefaultMapModel();

	}


	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}



	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}



	public String getOption() {
		return option;
	}



	public List<User> getAllUsers() {
		return allUsers;
	}



	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}



	public List<Smartphone> getPhones() {
		return phones;
	}



	public void setPhones(List<Smartphone> phones) {
		this.phones = phones;
	}



	public UserLocal getUserLocal() {
		return userLocal;
	}



	public void setUserLocal(UserLocal userLocal) {
		this.userLocal = userLocal;
	}



	public void setOption(String option) {
		this.option = option;
	}
	public List<Position> getAllPositions() {
		return allPositions;
	}

	public void setAllPositions(List<Position> allPositions) {
		this.allPositions = allPositions;
	}

	public PositionLocal getPositionLocal() {
		return positionLocal;
	}

	public void setPositionLocal(PositionLocal positionLocal) {
		this.positionLocal = positionLocal;
	}
	
	
}
