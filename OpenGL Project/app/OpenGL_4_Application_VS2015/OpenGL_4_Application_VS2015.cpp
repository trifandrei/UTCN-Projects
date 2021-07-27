//
//  main.cpp
//  OpenGL Advances Lighting
//
//  Created by CGIS on 28/11/16.
//  Copyright � 2016 CGIS. All rights reserved.
//

#define GLEW_STATIC

#include <iostream>
#include "glm/glm.hpp"//core glm functionality
#include "glm/gtc/matrix_transform.hpp"//glm extension for generating common transformation matrices
#include "glm/gtc/matrix_inverse.hpp"
#include "glm/gtc/type_ptr.hpp"
#include "GLEW/glew.h"
#include "GLFW/glfw3.h"
#include <string>
#include "Shader.hpp"
#include "Camera.hpp"
#include "SkyBox.hpp"
#define TINYOBJLOADER_IMPLEMENTATION

#include "Model3D.hpp"
#include "Mesh.hpp"

int glWindowWidth = 1240;
int glWindowHeight = 1060;
int SHADOW_WIDTH = 2048;
int SHADOW_HEIGHT = 2048;
int retina_width, retina_height;
GLFWwindow* glWindow = NULL;

glm::mat4 model;
GLuint modelLoc;
glm::mat4 view;
GLuint viewLoc;
glm::mat4 projection;
GLuint projectionLoc;
glm::mat3 normalMatrix;
GLuint normalMatrixLoc;
glm::mat3 lightDirMatrix;
GLuint lightDirMatrixLoc;

glm::vec3 lightDir;
GLuint lightDirLoc;
glm::vec3 lightColor;
GLuint lightColorLoc;

gps::Camera myCamera(glm::vec3(0.0f, 0.0f, 2.5f), glm::vec3(0.0f, 0.0f, -10.0f));
float cameraSpeed = 0.1f;
bool firstMouse = true;
float mouseSensitivity = 0.05f;
float lastX = glWindowWidth / 2.0;
float lastY = glWindowHeight / 2.0;
float yaw = -90.0f;
float pitch = 0.0f;
float rot = 0.0f;
bool pressedKeys[1024];
bool animate = false;
glm::vec3 positions[] = { glm::vec3(-0.173386,0.107392,-2.78924) , glm::vec3(-0.175742,0.107566,-2.88921),glm::vec3(-0.178098,0.107741,-2.98918), 
glm::vec3(-0.180454,0.107915,-3.08915), glm::vec3(-0.18281,0.10809,-3.18912), glm::vec3(-0.185166,0.108265,-3.2891),
glm::vec3(-0.187522,0.108439,-3.38907), glm::vec3(-0.189878,0.108614,-3.48904), glm::vec3(-0.192321,0.10905,-3.58901),
glm::vec3(-0.194851,0.109574,-3.68898), glm::vec3(-0.197382,0.110097,-3.78894), glm::vec3(-0.199912,0.110621,-3.88891),
glm::vec3(-0.202443,0.111144,-3.98888), glm::vec3(-0.204973,0.111668,-4.08884), glm::vec3(-0.207504,0.112192,-4.18881), 
glm::vec3(-0.210034,0.112715,-4.28877), glm::vec3(-0.212564,0.113239,-4.38874), glm::vec3(-0.215095,0.113762,-4.48871),
glm::vec3(-0.217625,0.114286,-4.58867) ,glm::vec3(-0.220156,0.114809,-4.68864), glm::vec3(-0.222686,0.115333,-4.78861),
glm::vec3(-0.225217,0.115857,-4.88857), glm::vec3(-0.227747,0.11638,-4.98854), glm::vec3(-0.230278,0.116904,-5.08851),
glm::vec3(-0.232808,0.117427,-5.18847), glm::vec3(-0.235338,0.117951,-5.28844), glm::vec3(-0.237869,0.118475,-5.38841),
glm::vec3(-0.240399,0.118998,-5.48837), glm::vec3(-0.24293,0.119522,-5.58834), glm::vec3(-0.24546,0.120045,-5.68831), 
glm::vec3(-0.247991,0.120569,-5.78827), glm::vec3(-0.250521,0.121093,-5.88824), glm::vec3(-0.253052,0.121616,-5.98821), 
glm::vec3(-0.255582,0.12214,-6.08817), glm::vec3(-0.258112,0.122663,-6.18814), glm::vec3(-0.260643,0.123187,-6.28811),
glm::vec3(-0.263173,0.123711,-6.38807), glm::vec3(-0.265704,0.124234,-6.48804), glm::vec3(-0.268234,0.124758,-6.58801), 
glm::vec3(-0.270765,0.125281,-6.68797), glm::vec3(-0.273295,0.125805,-6.78794), glm::vec3(-0.275826,0.126329,-6.8879), 
glm::vec3(-0.278356,0.126852,-6.98787), glm::vec3(-0.280887,0.127376,-7.08784), glm::vec3(-0.283417,0.127899,-7.1878), 
glm::vec3(-0.285948,0.128423,-7.28777), glm::vec3(-0.288478,0.128947,-7.38774), glm::vec3(-0.291008,0.12947,-7.4877), 
glm::vec3(-0.293539,0.129994,-7.58767), glm::vec3(-0.296069,0.130517,-7.68764), glm::vec3(-0.2986,0.131041,-7.7876), 
glm::vec3(-0.30113,0.131565,-7.88757), glm::vec3(-0.303661,0.132088,-7.98754), glm::vec3(-0.306191,0.132612,-8.0875),
glm::vec3(-0.308722,0.133135,-8.18747),glm::vec3(-0.311252,0.133659,-8.28744), glm::vec3(-0.313783,0.134183,-8.3874),
glm::vec3(-0.316313,0.134706,-8.48737), glm::vec3(-0.318843,0.13523,-8.58734), glm::vec3(-0.321374,0.135753,-8.68731),
glm::vec3(-0.323904,0.136277,-8.78727), glm::vec3(-0.326435,0.136801,-8.88724), glm::vec3(-0.328965,0.137324,-8.98721), 
glm::vec3(-0.331583,0.137935,-9.08717), glm::vec3(-0.334811,0.139593,-9.18711), glm::vec3(-0.3383,0.141949,-9.28702), 
glm::vec3(-0.341789,0.144392,-9.38693), glm::vec3(-0.345365,0.147184,-9.48682), glm::vec3(-0.349027,0.149977,-9.58672), 
glm::vec3(-0.35269,0.152769,-9.68661), glm::vec3(-0.356353,0.155561,-9.7865), glm::vec3(-0.360016,0.158353,-9.8864), 
glm::vec3(-0.363679,0.161145,-9.98629), glm::vec3(-0.367342,0.163937,-10.0862), glm::vec3(-0.371005,0.16673,-10.1861),
glm::vec3(-0.374668,0.169522,-10.286), glm::vec3(-0.378331,0.172314,-10.3859), glm::vec3(-0.381994,0.175106,-10.4858),
glm::vec3(-0.385657,0.177898,-10.5857), glm::vec3(-0.389407,0.18069,-10.6855), glm::vec3(-0.393158,0.183395,-10.7854), 
glm::vec3(-0.396908,0.186013,-10.8853), glm::vec3(-0.40031,0.188543,-10.9852), glm::vec3(-0.403711,0.191074,-11.0852),
glm::vec3(-0.40659,0.193517,-11.1851), glm::vec3(-0.407026,0.195175,-11.2851), glm::vec3(-0.395706,0.194302,-11.3844), 
glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844),
glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844),
glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.395706,0.194302,-11.3844
), glm::vec3(-0.395706,0.194302,-11.3844), glm::vec3(-0.385123,0.170873,-11.4811
), glm::vec3(-0.376371,0.148633,-11.5782), glm::vec3(-0.369287,0.128184,-11.6758
), glm::vec3(-0.364569,0.110132,-11.774), glm::vec3(-0.362585,0.0950055,-11.8729
), glm::vec3(-0.364841,0.0843791,-11.9723), glm::vec3(-0.370153,0.0786227,-12.072), 
glm::vec3(-0.377737,0.0787973,-12.1717), glm::vec3(-0.387297,0.0860341,-12.271), glm::vec3(-0.397715,0.10116,-12.3693), glm::vec3(-0.408974,0.12459,-12.4658
), glm::vec3(-0.420653,0.155325,-12.5603), glm::vec3(-0.432442,0.189281,-12.6536
), glm::vec3(-0.444149,0.223237,-12.7469), glm::vec3(-0.455938,0.257193,-12.8402
), glm::vec3(-0.467737,0.290902,-12.9336), glm::vec3(-0.47941,0.323789,-13.0273)
, glm::vec3(-0.49093,0.356428,-13.1212), glm::vec3(-0.50246,0.38882,-13.2151), glm::vec3(-0.377737, 0.0787973, -12.1717), glm::vec3(-0.387297, 0.0860341, -12.271), glm::vec3(-0.397715, 0.10116, -12.3693), glm::vec3(-0.408974, 0.12459, -12.4658
	), glm::vec3(-0.420653, 0.155325, -12.5603), glm::vec3(-0.432442, 0.189281, -12.6536
	), glm::vec3(-0.444149, 0.223237, -12.7469), glm::vec3(-0.455938, 0.257193, -12.8402
	), glm::vec3(-0.467737, 0.290902, -12.9336), glm::vec3(-0.47941, 0.323789, -13.0273)
	, glm::vec3(-0.49093, 0.356428, -13.1212), glm::vec3(-0.50246, 0.38882, -13.2151), glm::vec3(-0.513385, 0.419888, -13.3095), glm::vec3(-0.523906, 0.448289, -13.4048),
	glm::vec3(-0.534321, 0.474761, -13.5006), glm::vec3(-0.5447, 0.499545, -13.597), glm:: vec3(-0.654515, 0.523907, -13.6827), glm::vec3(-0.76433, 0.548268, -13.7684), glm::vec3(-0.874145, 0.57263, -13.8541), glm::vec3(-0.983957, 0.597076, -13.9398), glm::vec3(-0.994346, 0.621522, -14.0362),
	glm::vec3(-1.00473, 0.645968, -14.1326), glm::vec3(-1.01512, 0.670414, -14.229), glm::vec3(-1.02551, 0.69486, -14.3254), glm::vec3(-1.0359, 0.719307, -14.4219), glm::vec3(-1.04629, 0.743753, -14.5183),
	glm::vec3(-1.05667, 0.768199, -14.6147), glm::vec3(-1.06706, 0.792645, -14.7111), glm::vec3(-1.07745, 0.817091, -14.8075),
	glm::vec3(-1.08784, 0.841537, -14.9039), glm::vec3(-1.09765, 0.865475, -15.0005), glm::vec3(-1.10689, 0.88882, -15.0973), 
	glm::vec3(-1.11604, 0.91191, -15.1941), glm::vec3(-1.1252, 0.935, -15.291), glm::vec3(-1.13436, 0.958089, -15.3879), glm::vec3(-1.14351, 0.981179, -15.4847),
	glm::vec3(-1.15267, 1.00427, -15.5816), glm::vec3(-1.16183, 1.02736, -15.6785), glm::vec3(-1.17098, 1.05045, -15.7753), glm::vec3(-1.18014, 1.07354, -15.8722), glm::vec3(-1.1893, 1.09663, -15.9691),
	glm::vec3(-1.19845, 1.11972, -16.0659), glm::vec3(-1.20761, 1.14281, -16.1628), glm::vec3(-1.21668, 1.1659, -16.2597), glm::vec3(-1.22575, 1.18899, -16.3566),
	glm::vec3(-1.23482, 1.21208, -16.4534), glm::vec3(-1.2439, 1.23517, -16.5503), glm::vec3(-1.25297, 1.25826, -16.6472), 
	glm::vec3(-1.26204, 1.28135, -16.7441), glm::vec3(-1.27111, 1.30444, -16.8409), glm::vec3(-1.28018, 1.32753, -16.9378),
	glm::vec3(-1.31647, 1.41989, -17.3253), glm::vec3(-1.32515, 1.44162, -17.4225), glm::vec3(-1.33342, 1.46232, -17.52), glm::vec3(-1.34169, 1.48303, -17.6175), glm::vec3(-1.34997, 1.50373, -17.715), glm::vec3(-1.35815, 1.52444, -17.8125), 
	glm::vec3(-1.36634, 1.54515, -17.91), glm::vec3(-1.37461, 1.56585, -18.0074), glm::vec3(-1.38322, 1.58656, -18.1049), glm::vec3(-1.39226, 1.60718, -18.2023),
	glm::vec3(-1.4019	, 1.62729, -18.2998), glm::vec3(-1.41216, 1.64645, -18.3974), glm::vec3(-1.42311, 1.6651, -18.495), glm::vec3(-1.43424, 1.68359, -18.5927),glm::vec3(-1.44536, 1.70207, -18.6903), glm::vec3(-1.45649, 1.72055, -18.788), glm::vec3(-1.46762, 1.73903, -18.8856), glm::vec3(-1.47874, 1.75751, -18.9833), glm::vec3(-1.48987, 1.77599, -19.0809),
	glm::vec3(-1.50099, 1.79447, -19.1786), glm::vec3(-1.51212, 1.81295, -19.2762), glm::vec3(-1.52324, 1.83143, -19.3738), glm::vec3(-1.53437, 1.84991, -19.4715), glm::vec3(-1.54549, 1.86839, -19.5691), glm::vec3(-1.55662, 1.88688, -19.6668),
	glm::vec3(-1.56774, 1.90536, -19.7644), glm::vec3(-1.57887, 1.92384, -19.8621), glm::vec3(-1.58999, 1.94232, -19.9597), glm::vec3(-1.60112, 1.9608, -20.0574),
	glm::vec3(-1.61224, 1.97928, -20.155), glm::vec3(-1.62337, 1.99776, -20.2526), glm::vec3(-1.6345, 2.0159,-20.3504), glm::vec3(-1.64556, 2.03369, -20.4481), 
	glm::vec3(-1.65654, 2.05071, -20.5461), glm::vec3(-1.66711, 2.06687, -20.6442), glm::vec3(-1.67719, 2.08217, -20.7425), glm::vec3(-1.68718, 2.09713, -20.8409), glm::vec3(-1.69717, 2.11208, -20.9392), glm::vec3(-1.70716, 2.12703, -21.0376), 
	glm::vec3(-1.71715, 2.14199, -21.136), glm::vec3(-1.72715, 2.15694, -21.2343), glm::vec3(-1.73714, 2.17189, -21.3327), glm::vec3(	-1.74713, 2.18685, -21.4311), glm::vec3(-1.75712, 2.2018, -21.5295),
	glm::vec3(-1.76711, 2.21676, -21.6278), glm::vec3(-1.77711, 2.23171, -21.7262), glm::vec3(-1.7871, 2.24666, -21.8246), glm::vec3(-1.79709, 2.26162, -21.9229),
	glm::vec3(-1.90657, 2.27657, -22.0112), glm::vec3(-2.01605, 2.29152, -22.0995), glm::vec3(-2.12568, 2.30648, -22.1875), glm::vec3(-2.2354, 2.32143, -22.2755), glm::vec3(-2.34533, 2.33656, -22.3632), glm::vec3(-2.35609, 2.35186, -22.4614), glm::vec3(-2.36727, 2.36758, -22.5595),
	glm::vec3(-2.37862, 2.38349, -22.6576), glm::vec3(-2.38997, 2.39939, -22.7557), glm::vec3(-2.40131, 2.41529, -22.8538), glm::vec3(-2.41266, 2.43119, -22.9518), glm::vec3(-2.42401, 2.44709, -23.0499), glm::vec3(-2.43536, 2.463, -23.148), glm::vec3(-2.4467, 2.4789, -23.246), glm::vec3(-2.45805, 2.4948, -23.3441),
	glm::vec3(-2.4694, 2.5107, -23.4422), glm::vec3(-2.48075, 2.52661, -23.5403), glm::vec3(-2.49209, 2.54251, -23.6383), glm::vec3(-2.50353, 2.55841, -23.7364), glm::vec3(-2.51505, 2.57422, -23.8345), glm::vec3(-2.52623, 2.5897, -23.9326), glm::vec3(-2.53725, 2.60482, -24.0309),
	glm::vec3(-2.54785, 2.61926, -24.1292), glm::vec3(-2.55811, 2.63343, -24.2277), glm::vec3(-2.56837, 2.64761, -24.3262), glm::vec3(-2.57864, 2.66179, -24.4246), 
	glm::vec3(-2.5889, 2.67588, -24.5231), glm::vec3(-2.59916, 2.68997, -24.6216), glm::vec3(-2.60942, 2.70406, -24.72), glm::vec3(-2.61969, 2.71815, -24.8185), 
	glm::vec3(-2.62987, 2.73189, -24.917), glm::vec3(-2.6398, 2.74494, -25.0157), glm::vec3(-2.6494, 2.75722, -25.1144), glm::vec3(-2.65891, 2.76949, -25.2132), 
	glm::vec3(-2.66842, 2.78177, -25.312), glm::vec3(-2.67794, 2.79395, -25.4108), glm::vec3(-2.68745, 2.80614, -25.5096), glm::vec3(-2.69696, 2.81833, -25.6084), glm::vec3(-2.70648, 2.83051, -25.7072),
	glm::vec3(-2.71599, 2.8427, -25.806), glm::vec3(-2.72542, 2.85489, -25.9048), glm::vec3(-2.73502, 2.86707, -26.0036), glm::vec3(-2.74461, 2.87952, -26.1024), 
	glm::vec3(-2.7548, 2.89283, -26.2009), glm::vec3(-2.76583, 2.90744, -26.2993), glm::vec3(-2.77786, 2.92343, -26.3972), glm::vec3(-2.79122, 2.94105, -26.4948),
	glm::vec3(-2.80597,2.96056, -26.5917), glm::vec3(-2.8216, 2.98161, -26.6882), glm::vec3(-2.83802, 3.00402, -26.7843), glm::vec3(-2.85506, 3.02753, -26.88),
	glm::vec3(-2.87281, 3.05215, -26.9753), glm::vec3(-2.89117, 3.07778, -27.0702), glm::vec3(-2.91006, 3.10442, -27.1647), glm::vec3(-2.92917, 3.13148, -27.259),
	glm::vec3(-2.9489, 3.15912, -27.3531), glm::vec3(-2.96886, 3.18711, -27.447), glm::vec3(-2.98912, 3.21559, -27.5407), glm::vec3(-3.00961, 3.24433, -27.6343),
	glm::vec3(-3.03025, 3.27315, -27.7278), glm::vec3(-3.05089, 3.30197, -27.8213), glm::vec3(-3.07162, 3.33079, -27.9147), glm::vec3(-3.09234, 3.35969, -28.0082), glm::vec3(-3.11291, 3.38834, -28.1018), glm::vec3(-3.13321,3.41624, -28.1956), 
	glm::vec3(-3.15307, 3.44305, -28.2899), glm::vec3(-3.17258, 3.46885, -28.3845), glm::vec3(-3.19174, 3.49346, -28.4795), glm::vec3(-3.21011, 3.51715,-28.575),
	glm::vec3(-3.22816, 3.54049, -28.6705), glm::vec3(-3.24614, 3.56375, -28.7661), glm::vec3(-3.26388, 3.58659, -28.8618),
	glm::vec3(-3.29829, 3.62996, -29.054), glm::vec3(-3.31501, 3.65101, -29.1503), glm::vec3(-3.33173, 3.67205, -29.2466),
	glm::vec3(-3.34846, 3.6931, -29.3429), glm::vec3(-3.36518, 3.71415, -29.4393), glm::vec3(-3.38191, 3.73519, -29.5356), glm::vec3(-3.39863, 3.75624, -29.6319), 
	glm::vec3(-3.41535, 3.77729, -29.7282),
	glm::vec3(-3.43208, 3.79834, -29.8245), glm::vec3(-3.44872, 3.81921, -29.9209), glm::vec3(-3.46497, 3.83958, -30.0175), glm::vec3(-3.48089, 3.85943, -30.1142),
	glm::vec3(-3.49657, 3.87902, -30.211), glm::vec3(-3.51183, 3.89836, -30.3079), glm::vec3(-3.5266, 3.91736, -30.405), glm::vec3(-3.54087, 3.93601, -30.5022),
	glm::vec3(-3.55446, 3.95423, -30.5995), glm::vec3(-3.56764, 3.97228, -30.697), glm::vec3(-3.5804, 3.99016, -30.7946), glm::vec3(-3.59307, 4.00796, -30.8921),
	glm::vec3(-3.60575, 4.02575, -30.9897), glm::vec3(-3.61842, 4.04355, -31.0873), glm::vec3(-3.6311, 4.06134, -31.1849), glm::vec3(-3.64377, 4.07913, -31.2825),
	glm::vec3(-3.65644, 4.09693, -31.3801),
	glm::vec3(-3.66912, 4.11472, -31.4776), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752),
	glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), 
	glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752),glm::vec3(-3.68179, 4.13252, -31.5752), 
	glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752), glm::vec3(-3.68179, 4.13252, -31.5752) };
int i;
float angle = 0.0f;
GLfloat lightAngle;



gps::Model3D tankbody1;
gps::Model3D tankbody2;
gps::Model3D tank2;
gps::Model3D tankhead1;
gps::Model3D tankhead2;
gps::Model3D ground1;
gps::Model3D ground2;
gps::Model3D tower;
gps::Model3D jeep;
gps::Model3D house2;
gps::Model3D tent1;
gps::Model3D tent2;
gps::Model3D tent;
gps::Model3D watert;
gps::Model3D wall;
gps::Model3D wall2;
gps::Model3D wall3;

gps::Model3D wall4;
gps::Model3D gate1;
gps::Model3D gate2;
gps::Model3D tree;



gps::Model3D house1;
gps::Model3D lightCube1;
gps::Model3D lightCube;
gps::Shader myCustomShader;
gps::Shader lightShader;
gps::Shader depthMapShader;

GLint texture;
GLint texturetower;

GLuint shadowMapFBO;
GLuint depthMapTexture;

std::vector<const GLchar*> faces;

gps::SkyBox mySkyBox;
gps::Shader skyboxShader;

GLenum glCheckError_(const char *file, int line)
{
	GLenum errorCode;
	while ((errorCode = glGetError()) != GL_NO_ERROR)
	{
		std::string error;
		switch (errorCode)
		{
		case GL_INVALID_ENUM:                  error = "INVALID_ENUM"; break;
		case GL_INVALID_VALUE:                 error = "INVALID_VALUE"; break;
		case GL_INVALID_OPERATION:             error = "INVALID_OPERATION"; break;
		case GL_STACK_OVERFLOW:                error = "STACK_OVERFLOW"; break;
		case GL_STACK_UNDERFLOW:               error = "STACK_UNDERFLOW"; break;
		case GL_OUT_OF_MEMORY:                 error = "OUT_OF_MEMORY"; break;
		case GL_INVALID_FRAMEBUFFER_OPERATION: error = "INVALID_FRAMEBUFFER_OPERATION"; break;
		}
		std::cout << error << " | " << file << " (" << line << ")" << std::endl;
	}
	return errorCode;
}
#define glCheckError() glCheckError_(__FILE__, __LINE__)

void windowResizeCallback(GLFWwindow* window, int width, int height)
{
	fprintf(stdout, "window resized to width: %d , and height: %d\n", width, height);
	//TODO
	//for RETINA display
	glfwGetFramebufferSize(glWindow, &retina_width, &retina_height);

	//set projection matrix
	glm::mat4 projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	//send matrix data to shader
	GLint projLoc = glGetUniformLocation(myCustomShader.shaderProgram, "projection");
	glUniformMatrix4fv(projLoc, 1, GL_FALSE, glm::value_ptr(projection));

	//set Viewport transform
	glViewport(0, 0, retina_width, retina_height);
}

void keyboardCallback(GLFWwindow* window, int key, int scancode, int action, int mode)
{
	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
		glfwSetWindowShouldClose(window, GL_TRUE);

	if (key >= 0 && key < 1024)
	{
		if (action == GLFW_PRESS)
			pressedKeys[key] = true;
		else if (action == GLFW_RELEASE)
			pressedKeys[key] = false;
	}
}

void mouseCallback(GLFWwindow* window, double xpos, double ypos)
{
		

		if (firstMouse) {
			lastX = xpos;
			lastY = ypos;
			firstMouse = false;
		}

		float xoffset = xpos - lastX;
		float yoffset = lastY - ypos; //reversed, y coords go from bottom to top
		lastX = xpos;
		lastY = ypos;

		xoffset *= mouseSensitivity;
		yoffset *= mouseSensitivity;

		yaw += xoffset;
		pitch += yoffset;
		myCamera.rotate(pitch, yaw);

}


void processMovement()
{

	if (pressedKeys[GLFW_KEY_Q]) {
		angle += 0.1f;
		if (angle > 360.0f)
			angle -= 360.0f;
	}

	if (pressedKeys[GLFW_KEY_E]) {
		angle -= 0.1f;
		if (angle < 0.0f)
			angle += 360.0f;
	}

	if (pressedKeys[GLFW_KEY_W]) {
		myCamera.move(gps::MOVE_FORWARD, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_S]) {
		myCamera.move(gps::MOVE_BACKWARD, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_A]) {
		myCamera.move(gps::MOVE_LEFT, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_D]) {
		myCamera.move(gps::MOVE_RIGHT, cameraSpeed);
	}

	if (pressedKeys[GLFW_KEY_R]) {
		glPolygonMode(GL_FRONT_AND_BACK, GL_POINT);
		
	}

	if (pressedKeys[GLFW_KEY_X]) {
		rot += 0.0002f;
	   model = glm::rotate( 3.14f, glm::vec3(0, 1, 0));
	
	}
	if (pressedKeys[GLFW_KEY_T]) {
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	}
	if (pressedKeys[GLFW_KEY_Y]) {
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
	if (pressedKeys[GLFW_KEY_J]) {

		lightAngle += 0.5f;
		if (lightAngle > 360.0f)
			lightAngle -= 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}

	if (pressedKeys[GLFW_KEY_L]) {
		lightAngle -= 0.3f;
		if (lightAngle < 0.0f)
			lightAngle += 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}
	if (pressedKeys[GLFW_KEY_M]) {

		rot += 0.5f;
		if (rot > 360.0f)
			rot -= 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(rot), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}

	if (pressedKeys[GLFW_KEY_N]) {
		rot -= 0.3f;
		if (rot < 0.0f)
			rot += 360.0f;
		glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(rot), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
		myCustomShader.useShaderProgram();
		glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDirTr));
	}
	

	if(pressedKeys[GLFW_KEY_C]) {
		animate = true;
	}

	if (animate) {

		if (i < sizeof(positions) / sizeof(glm::vec3)) {

			myCamera.setPosition(positions[i]);
			i++;
			return;
		}
		i = 0;
		animate = false;
	}
}

bool initOpenGLWindow()
{
	if (!glfwInit()) {
		fprintf(stderr, "ERROR: could not start GLFW3\n");
		return false;
	}

	//for Mac OS X
	//glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
	//glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
	//glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
	//glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

	glWindow = glfwCreateWindow(glWindowWidth, glWindowHeight, "OpenGL Shader Example", NULL, NULL);
	if (!glWindow) {
		fprintf(stderr, "ERROR: could not open window with GLFW3\n");
		glfwTerminate();
		return false;
	}

	glfwSetWindowSizeCallback(glWindow, windowResizeCallback);
	glfwMakeContextCurrent(glWindow);

	glfwWindowHint(GLFW_SAMPLES, 4);

	// start GLEW extension handler
	glewExperimental = GL_TRUE;
	glewInit();

	// get version info
	const GLubyte* renderer = glGetString(GL_RENDERER); // get renderer string
	const GLubyte* version = glGetString(GL_VERSION); // version as a string
	printf("Renderer: %s\n", renderer);
	printf("OpenGL version supported %s\n", version);

	//for RETINA display
	glfwGetFramebufferSize(glWindow, &retina_width, &retina_height);

	glfwSetKeyCallback(glWindow, keyboardCallback);
	glfwSetCursorPosCallback(glWindow, mouseCallback);
    glfwSetInputMode(glWindow, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

	return true;
}

void initOpenGLState()
{
	glClearColor(0.3, 0.3, 0.3, 1.0);
	glViewport(0, 0, retina_width, retina_height);

	glEnable(GL_DEPTH_TEST); // enable depth-testing
	glDepthFunc(GL_LESS); // depth-testing interprets a smaller value as "closer"
	glEnable(GL_CULL_FACE); // cull face
	glCullFace(GL_BACK); // cull back face
	glFrontFace(GL_CCW); // GL_CCW for counter clock-wise
	
}

void initFBOs()
{
	//generate FBO ID
	glGenFramebuffers(1, &shadowMapFBO);

	//create depth texture for FBO
	glGenTextures(1, &depthMapTexture);
	glBindTexture(GL_TEXTURE_2D, depthMapTexture);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT,
		SHADOW_WIDTH, SHADOW_HEIGHT, 0, GL_DEPTH_COMPONENT, GL_FLOAT, NULL);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

	//attach texture to FBO
	glBindFramebuffer(GL_FRAMEBUFFER, shadowMapFBO);
	glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthMapTexture, 0);
	glDrawBuffer(GL_NONE);
	glReadBuffer(GL_NONE);
	glBindFramebuffer(GL_FRAMEBUFFER, 0);
}

glm::mat4 computeLightSpaceTrMatrix()
{
	const GLfloat near_plane = 1.0f, far_plane = 10.0f;
	glm::mat4 lightProjection = glm::ortho(-20.0f, 20.0f, -20.0f, 20.0f, near_plane, far_plane);

	glm::vec3 lightDirTr = glm::vec3(glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 1.0f, 0.0f)) * glm::vec4(lightDir, 1.0f));
	glm::mat4 lightView = glm::lookAt(myCamera.getCameraTarget() + 1.0f * lightDirTr, myCamera.getCameraTarget(), glm::vec3(0.0f, 1.0f, 0.0f));

	return lightProjection * lightView;
}

void initModels()
{

	jeep = gps::Model3D("objects/jeep/jeep.obj", "objects/jeep/");
	tower = gps::Model3D("objects/tower/tower.obj","objects/tower/");

	watert = gps::Model3D("objects/bar1/bar1.obj", "objects/bar1/");

	house1= gps::Model3D("objects/house1/house1.obj", "objects/house1/");

	wall= gps::Model3D("objects/steel fence1/fance1.obj", "objects/steel fence1/");
	wall2 = gps::Model3D("objects/steel fence2/wall2.obj", "objects/steel fence2/");
	wall3 = gps::Model3D("objects/steel fence3/wall3.obj", "objects/steel fence3/");
	wall4 = gps::Model3D("objects/steel fence4/wall4.obj", "objects/steel fence4/");

	gate1 = gps::Model3D("objects/gate1/gate1.obj", "objects/gate1/");
	gate2 = gps::Model3D("objects/gate2/gate2.obj", "objects/gate2/");

	tree = gps::Model3D("objects/tree/tree.obj", "objects/tree/");
	house2= gps::Model3D("objects/house2/house2.obj", "objects/house2/");

	ground1 = gps::Model3D("objects/ground1/ground1.obj", "objects/ground1/");
	ground2 = gps::Model3D("objects/ground2/ground2.obj", "objects/ground2/");

	tankbody1 = gps::Model3D("objects/tankbody1/tankbody1.obj", "objects/tankbody1/");
	tankbody2 = gps::Model3D("objects/tankbody2/tank2.obj", "objects/tankbody2/");

	tankhead1 = gps::Model3D("objects/tankhead1/tankhead1.obj", "objects/tankhead1/");
	tankhead2 = gps::Model3D("objects/tankhead2/tankhead2.obj", "objects/tankhead2/");

	tent = gps::Model3D("objects/tent/tent.obj", "objects/tent/");
	tent1 = gps::Model3D("objects/tent1/tent.obj", "objects/tent1/");
	tent2 = gps::Model3D("objects/tent2/tent.obj", "objects/tent2/");

	tank2= gps::Model3D("objects/tank2/tank2.obj", "objects/tank2/");



	lightCube1 = gps::Model3D("objects/cube/cube.obj", "objects/cube/");
	lightCube = gps::Model3D("objects/cube/cube.obj", "objects/cube/");
	faces.push_back("objects/skybox/right.tga");
	faces.push_back("objects/skybox/left.tga");
	faces.push_back("objects/skybox/top.tga");
	faces.push_back("objects/skybox/bottom.tga");
	faces.push_back("objects/skybox/back.tga");
	faces.push_back("objects/skybox/front.tga");
	mySkyBox.Load(faces);

}

void initShaders()
{
	myCustomShader.loadShader("shaders/shaderStart.vert", "shaders/shaderStart.frag");
	lightShader.loadShader("shaders/lightCube.vert", "shaders/lightCube.frag");
	depthMapShader.loadShader("shaders/simpleDepthMap.vert", "shaders/simpleDepthMap.frag");
	skyboxShader.loadShader("shaders/skyboxShader.vert", "shaders/skyboxShader.frag");
}

void initUniforms()
{
	myCustomShader.useShaderProgram();

	modelLoc = glGetUniformLocation(myCustomShader.shaderProgram, "model");

	viewLoc = glGetUniformLocation(myCustomShader.shaderProgram, "view");

	normalMatrixLoc = glGetUniformLocation(myCustomShader.shaderProgram, "normalMatrix");

	lightDirMatrixLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightDirMatrix");

	projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	projectionLoc = glGetUniformLocation(myCustomShader.shaderProgram, "projection");
	glUniformMatrix4fv(projectionLoc, 1, GL_FALSE, glm::value_ptr(projection));

	//set the light direction (direction towards the light)
	lightDir = glm::vec3(0.0f, 1.0f, 2.0f);
	lightDirLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightDir");
	glUniform3fv(lightDirLoc, 1, glm::value_ptr(lightDir));

	//set light color
	lightColor = glm::vec3(1.0f, 1.0f, 1.0f); //white light
	lightColorLoc = glGetUniformLocation(myCustomShader.shaderProgram, "lightColor");
	glUniform3fv(lightColorLoc, 1, glm::value_ptr(lightColor));

	lightShader.useShaderProgram();
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "projection"), 1, GL_FALSE, glm::value_ptr(projection));
}




void renderScene()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	processMovement();

	//render the scene to the depth buffer (first pass)

	depthMapShader.useShaderProgram();

	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "lightSpaceTrMatrix"),
		1,
		GL_FALSE,
		glm::value_ptr(computeLightSpaceTrMatrix()));

	glViewport(0, 0, SHADOW_WIDTH, SHADOW_HEIGHT);
	glBindFramebuffer(GL_FRAMEBUFFER, shadowMapFBO);
	glClear(GL_DEPTH_BUFFER_BIT);

	//create model matrix for tower
	model = glm::rotate(glm::mat4(1.0f), glm::radians(angle), glm::vec3(0, 1, 0));
	//send model matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),1,GL_FALSE,glm::value_ptr(model));
	tower.Draw(depthMapShader);

	model = glm::rotate(glm::mat4(1.0f), glm::radians(angle), glm::vec3(0, 1, 0));
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),1,GL_FALSE,glm::value_ptr(model));
	gate2.Draw(depthMapShader);

	model = glm::rotate(glm::mat4(1.0f), glm::radians(angle), glm::vec3(0, 1, 0));
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));
	gate1.Draw(depthMapShader);

	model = glm::rotate(glm::mat4(1.0f), glm::radians(angle), glm::vec3(0, 1, 0));
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));
	house1.Draw(depthMapShader);
	
	model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, -1.0f, 0.0f));
	glUniformMatrix4fv(glGetUniformLocation(depthMapShader.shaderProgram, "model"),	1,	GL_FALSE,glm::value_ptr(model));
	jeep.Draw(depthMapShader);

	

	
	glBindFramebuffer(GL_FRAMEBUFFER, 0);

	//render the scene (second pass)

	myCustomShader.useShaderProgram();

	//send lightSpace matrix to shader
	glUniformMatrix4fv(glGetUniformLocation(myCustomShader.shaderProgram, "lightSpaceTrMatrix"),
		1,
		GL_FALSE,
		glm::value_ptr(computeLightSpaceTrMatrix()));

	//send view matrix to shader
	view = myCamera.getViewMatrix();
	glUniformMatrix4fv(glGetUniformLocation(myCustomShader.shaderProgram, "view"),
		1,
		GL_FALSE,
		glm::value_ptr(view));

	//compute light direction transformation matrix
	lightDirMatrix = glm::mat3(glm::inverseTranspose(view));
	//send lightDir matrix data to shader
	glUniformMatrix3fv(lightDirMatrixLoc, 1, GL_FALSE, glm::value_ptr(lightDirMatrix));

	glViewport(0, 0, retina_width, retina_height);
	myCustomShader.useShaderProgram();

	glActiveTexture(GL_TEXTURE3);
	glBindTexture(GL_TEXTURE_2D, depthMapTexture);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "shadowMap"), 3);

	//tent

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	glActiveTexture(GL_TEXTURE0);
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));
	//glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tent.Draw(myCustomShader);

	//tent2

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tent1.Draw(myCustomShader);

	//tent3

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tent2.Draw(myCustomShader);

	///ground1
	//model = glm::scale(model, glm::vec3(0.0f, 2.0f, 0.0f));
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	ground1.Draw(myCustomShader);


	///ground2
	//model = glm::rotate(model, 3.14f, glm::vec3(0, 1, 0));
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	ground2.Draw(myCustomShader);

	//tankhead1
	
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	//model = glm::rotate(glm::mat4(1.0f), glm::radians(rot), glm::vec3(0, 1, 0));
	tankhead1.Draw(myCustomShader);

	//tankhead1

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tankhead2.Draw(myCustomShader);
	//tankbody2

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tankbody2.Draw(myCustomShader);

	//tankbody1

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tankbody1.Draw(myCustomShader);


	//tree
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tree.Draw(myCustomShader);



	//tank2

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tank2.Draw(myCustomShader);

	//jeep2

	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	normalMatrix = glm::mat3(glm::inverseTranspose(view*model));
	glActiveTexture(GL_TEXTURE0);
	glUniformMatrix3fv(normalMatrixLoc, 1, GL_FALSE, glm::value_ptr(normalMatrix));
	jeep.Draw(myCustomShader);



	//tower
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	tower.Draw(myCustomShader);

	//model = glm::rotate(model, 3.14f, glm::vec3(0, 1, 0));
	//send matrix data to vertex shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	house1.Draw(myCustomShader);

	//casa mare
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	house2.Draw(myCustomShader);

	//wall
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	wall.Draw(myCustomShader);


	//wall2
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	wall2.Draw(myCustomShader);

	//wall3
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	wall3.Draw(myCustomShader);

	//wall4
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	wall4.Draw(myCustomShader);

	//gate1
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	gate1.Draw(myCustomShader);

	//gate2
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	gate2.Draw(myCustomShader);

	//send matrix data to vertex shader
	glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));
	glActiveTexture(GL_TEXTURE0);
	glUniform1i(glGetUniformLocation(myCustomShader.shaderProgram, "diffuseTexture"), 0);
	watert.Draw(myCustomShader);


	//draw a white cube around the light

	lightShader.useShaderProgram();

	


	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "view"), 1, GL_FALSE, glm::value_ptr(view));

	model = glm::rotate(glm::mat4(1.0f), glm::radians(lightAngle), glm::vec3(0.0f, 10.0f, 0.0f));
	model = glm::translate(model, 12.0f * lightDir);
	model = glm::scale(model, glm::vec3(0.05f, 0.05f, 0.05f));
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));
	lightCube.Draw(lightShader);

	lightShader.useShaderProgram();
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "view"), 1, GL_FALSE, glm::value_ptr(view));

	model = glm::rotate(glm::mat4(1.0f), glm::radians(rot), glm::vec3(0.0f, 10.0f, 0.0f));
	model = glm::translate(model, 2.0f * lightDir);
	model = glm::scale(model, glm::vec3(0.05f, 0.05f, 0.05f));
	glUniformMatrix4fv(glGetUniformLocation(lightShader.shaderProgram, "model"), 1, GL_FALSE, glm::value_ptr(model));
	lightCube1.Draw(lightShader);

	skyboxShader.useShaderProgram();

	view = myCamera.getViewMatrix();
	glUniformMatrix4fv(glGetUniformLocation(skyboxShader.shaderProgram, "view"), 1, GL_FALSE,
		glm::value_ptr(view));

	projection = glm::perspective(glm::radians(45.0f), (float)retina_width / (float)retina_height, 0.1f, 1000.0f);
	glUniformMatrix4fv(glGetUniformLocation(skyboxShader.shaderProgram, "projection"), 1, GL_FALSE,
		glm::value_ptr(projection));
	mySkyBox.Draw(skyboxShader, view, projection);

	
	

}




int main(int argc, const char * argv[]) {


	initOpenGLWindow();
	initOpenGLState();
	initFBOs();
	initModels();
	initShaders();
	initUniforms();
	glCheckError();

	

	while (!glfwWindowShouldClose(glWindow)) {
		renderScene();
		
		glfwPollEvents();
		glfwSwapBuffers(glWindow);
	}

	//close GL context and any other GLFW resources
	glfwTerminate();

	return 0;
}
