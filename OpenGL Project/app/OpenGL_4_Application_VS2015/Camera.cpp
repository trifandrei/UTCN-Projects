//
//  Camera.cpp
//  Lab5
//
//  Created by CGIS on 28/10/2016.
//  Copyright © 2016 CGIS. All rights reserved.
//

#include "Camera.hpp"

namespace gps {

	Camera::Camera(glm::vec3 cameraPosition, glm::vec3 cameraTarget)
	{
		this->cameraPosition = cameraPosition;
		this->cameraTarget = cameraTarget;
		this->cameraDirection = glm::normalize(cameraTarget - cameraPosition);
		this->cameraRightDirection = glm::normalize(glm::cross(this->cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)));
	}

	glm::mat4 Camera::getViewMatrix()
	{
		return glm::lookAt(cameraPosition, cameraPosition + cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f));
	}

	glm::vec3 Camera::getCameraTarget()
	{
		return cameraTarget;
	}

	
	void Camera::move(MOVE_DIRECTION direction, float speed)
	{
		switch (direction) {
		case MOVE_FORWARD:
			cameraPosition += cameraDirection * speed;
			break;

		case MOVE_BACKWARD:
			cameraPosition -= cameraDirection * speed;
			break;

		case MOVE_RIGHT:
			cameraPosition += cameraRightDirection * speed;
			break;

		case MOVE_LEFT:
			cameraPosition -= cameraRightDirection * speed;
			break;
		}
	}

	void Camera::rotate(float pitch, float yaw)
	{
		//screen doesnt get flipped
		if (pitch > 89.0f) pitch = 89.0f;
		if (pitch < -89.0f) pitch = -89.0f;

		//update camera front/direction
		glm::vec3 front;
		front.x = cos(glm::radians(yaw)) * cos(glm::radians(pitch));
		front.y = sin(glm::radians(pitch));
		front.z = sin(glm::radians(yaw)) * cos(glm::radians(pitch));

		cameraDirection = glm::normalize(front);
		cameraRightDirection = glm::normalize(glm::cross(this->cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)));
	}
	glm::vec3 Camera::getPosition() {
		return this->cameraPosition;
	}

	void Camera::setPosition(glm::vec3 position) {
		this->cameraPosition = position;
	}
}
