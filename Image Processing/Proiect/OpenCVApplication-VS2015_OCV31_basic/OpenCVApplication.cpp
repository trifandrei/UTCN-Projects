// OpenCVApplication.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "common.h"
#include <conio.h>
#include <queue>
#include <random>
#include <vector>
#include <math.h>

#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <opencv2/videoio.hpp>
#include <opencv2/core.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <stdio.h>
using namespace std;

/////////////////////////////Lab11///////////////////////////////////////////
void Prewitt()
{
	char fname[MAX_PATH];
	openFileDlg(fname);
	//citim imaginea si declaram cele 2 imagini de output
	Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	Mat dest_x = src.clone();
	Mat dest_y = src.clone();

	//declaram filtru vertical
	int Hx[3][3] = { { -1, 0, 1 },{ -1, 0, 1 },{ -1, 0, 1 } };
	//declaram filtru orizontal
	int Hy[3][3] = { { 1, 1, 1 },{ 0, 0, 0 },{ -1, -1, -1 } };

	int k = 1;

	for (int y = k; y < src.rows - k; y++)
	{
		for (int x = k; x < src.cols - k; x++)
		{
			int aux1 = 0, aux2 = 0;
			for (int i = -k; i <= k; i++)
			{
				for (int j = -k; j <= k; j++)
				{	//parcurgem imaginea incepand cu cu linia 2 coloana 2 pana la penultima linie/coloana 
					//pe fiecare din acesti pixeli aplicam chernelul de 3x3 si calculam gradientul pe diraectia x si y
					aux1 += src.at<uchar>(x + j, y + i) * Hx[j + k][i + k];//GX
					aux2 += src.at<uchar>(x + j, y + i) * Hy[j + k][i + k];//GY
				}
				//daca gradientul are semn negativ atunci pixelului ii aribuim valoare 0	
				if (aux1 < 0)
					dest_x.at<uchar>(x, y) = 0;
				else if (aux1 > 255)
					dest_x.at<uchar>(x, y) = 255; //daca gradientul are semn pozitiv atunci pixelului ii aribuim valoare 255
				else
					dest_x.at<uchar>(x, y) = aux1;//daca valoarea este inte 0 si 255 punem acea valoare

				//la fel pentru imaginea cu gradientul vertical
				if (aux2 < 0)
					dest_y.at<uchar>(x, y) = 0;
				else if (aux2 > 255)
					dest_y.at<uchar>(x, y) = 255;
				else
					dest_y.at<uchar>(x, y) = aux2;

			}
		}
	}

	imshow("Original", src);
	imshow("X ", dest_x);
	imshow("Y ", dest_y);
	waitKey(0);
}
void Roberts()
{
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	Mat dest_x = src.clone();
	Mat dest_y = src.clone();

	int Hx[2][2] = { { 1, 0 },
					{ 0, -1 } };

	int Hy[2][2] = { { 0, -1 },
					{ 1, 0 } };

	
	int k = 1;



	for (int y = k; y < src.rows - k; y++)
	{
		for (int x = k; x < src.cols - k; x++)
		{
			int aux1 = 0, aux2 = 0;
			for (int i = -k; i < k; i++)
			{
				for (int j = -k; j < k; j++)
				{
					aux1 += src.at<uchar>(x + j, y + i) * Hx[j + k][i + k];
					aux2 += src.at<uchar>(x + j, y + i) * Hy[j + k][i + k];
				}

				if (aux1 < 0)
					dest_x.at<uchar>(x, y) = 0;
				else if (aux1 > 255)
					dest_x.at<uchar>(x, y) = 255;
				else
					dest_x.at<uchar>(x, y) = aux1;

				if (aux2 < 0)
					dest_y.at<uchar>(x, y) = 0;
				else if (aux2 > 255)
					dest_y.at<uchar>(x, y) = 255;
				else
					dest_y.at<uchar>(x, y) = aux2;

			}
		}
	}

	imshow("Original", src);
	imshow("X ", dest_x);
	imshow("Y ", dest_y);
	waitKey(0);
}
void  Sobel()
{
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	Mat dest_x = src.clone();
	Mat dest_y = src.clone();


	int Hx[3][3] = { { -1, 0, 1 },
					 { -2, 0, 2 },
					 { -1, 0, 1 } };

	int Hy[3][3] = { { 1, 2, 1 },
					 { 0, 0, 0 },
					{ -1, -2, -1}
	};

	
	int k = 1;

	

	for (int y = k; y < src.rows - k; y++)
	{
		for (int x = k; x < src.cols - k; x++)
		{
			int aux1 = 0, aux2 = 0;
			for (int i = -k; i <= k; i++)
			{
				for (int j = -k; j <= k; j++)
				{
					aux1 += src.at<uchar>(x + j, y + i) * Hx[j + k][i + k];
					aux2 += src.at<uchar>(x + j, y + i) * Hy[j + k][i + k];
				}

				if (aux1 < 0)
					dest_x.at<uchar>(x, y) = 0;
				else if (aux1 > 255)
					dest_x.at<uchar>(x, y) = 255;
				else
					dest_x.at<uchar>(x, y) = aux1;

				if (aux2 < 0)
					dest_y.at<uchar>(x, y) = 0;
				else if (aux2 > 255)
					dest_y.at<uchar>(x, y) = 255;
				else
					dest_y.at<uchar>(x, y) = aux2;

			}
		}
	}

	imshow("Original", src);
	imshow("X ", dest_x);
	imshow("Y ", dest_y);
	waitKey(0);
}
void PrewittM()
{
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	Mat dest_x = src.clone();
	Mat dest_y = src.clone();


	int Hx[3][3] = { { -1, 0, 1 },
	{ -1, 0, 1 },
	{ -1, 0, 1 } };

	int Hy[3][3] = { { 1, 1, 1 },
	{ 0, 0, 0 },
	{ -1, -1, -1 } };

	
	int k = 1;


	for (int y = k; y < src.rows - k; y++)
	{
		for (int x = k; x < src.cols - k; x++)
		{
			int aux1 = 0, aux2 = 0;
			for (int i = -k; i <= k; i++)
			{
				for (int j = -k; j <= k; j++)
				{
					aux1 += src.at<uchar>(x + j, y + i) * Hx[j + k][i + k];
					aux2 += src.at<uchar>(x + j, y + i) * Hy[j + k][i + k];
				}

				if (aux1 < 0)
					dest_x.at<uchar>(x, y) = 0;
				else if (aux1 > 255)
					dest_x.at<uchar>(x, y) = 255;
				else
					dest_x.at<uchar>(x, y) = aux1;

				if (aux2 < 0)
					dest_y.at<uchar>(x, y) = 0;
				else if (aux2 > 255)
					dest_y.at<uchar>(x, y) = 255;
				else
					dest_y.at<uchar>(x, y) = aux2;

			}
		}
	}

	Mat module = src.clone();
	Mat direction = src.clone();

	for (int i = 1; i < src.rows - 1; i++)
		for (int j = 1; j < src.cols - 1; j++)
		{	//calculam modulul si directia  conform formulei din laboratror
			module.at<uchar>(i, j) = (uchar)sqrt((dest_x.at<uchar>(i, j) * dest_x.at<uchar>(i, j)) + (dest_y.at<uchar>(i, j) * dest_y.at<uchar>(i, j)));
			if (dest_y.at<uchar>(i, j) != 0)
	
				direction.at<uchar>(i, j) = (uchar)atan(dest_x.at<uchar>(i, j) / dest_y.at<uchar>(i, j));
			else
				direction.at<uchar>(i, j) = 0;
		}

	Mat binarizarea = module.clone();
	int tresh = 128;

	for (int i = 0; i < module.rows; i++)
		for (int j = 0; j < module.cols; j++)
			if (module.at<uchar>(i, j) < 128)
				binarizarea.at<uchar>(i, j) = 0;
			else
				binarizarea.at<uchar>(i, j) = 255;

	imshow("Original", src);
	imshow("Module", module);

	waitKey(0);
}
void SobelM()
{
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	Mat dest_x = src.clone();
	Mat dest_y = src.clone();


	int Hx[3][3] = { { -1, 0, 1 },
						{ -2, 0, 2 },
							{ -1, 0, 1 } };

	int Hy[3][3] = { { 1, 2, 1 },
						{ 0, 0, 0 },
						{ -1, -2, -1 } 
	};
	int k = 1;


	for (int y = k; y < src.rows - k; y++)
	{
		for (int x = k; x < src.cols - k; x++)
		{
			int aux1 = 0, aux2 = 0;
			for (int i = -k; i <= k; i++)
			{
				for (int j = -k; j <= k; j++)
				{
					aux1 += src.at<uchar>(x + j, y + i) * Hx[j + k][i + k];
					aux2 += src.at<uchar>(x + j, y + i) * Hy[j + k][i + k];
				}

				if (aux1 < 0)
					dest_x.at<uchar>(x, y) = 0;
				else if (aux1 > 255)
					dest_x.at<uchar>(x, y) = 255;
				else
					dest_x.at<uchar>(x, y) = aux1;

				if (aux2 < 0)
					dest_y.at<uchar>(x, y) = 0;
				else if (aux2 > 255)
					dest_y.at<uchar>(x, y) = 255;
				else
					dest_y.at<uchar>(x, y) = aux2;

			}
		}
	}

	Mat module = src.clone();
	Mat direction = src.clone();

	for (int i = 1; i < src.rows - 1; i++)
		for (int j = 1; j < src.cols - 1; j++)
		{
			module.at<uchar>(i, j) = (uchar)sqrt((dest_x.at<uchar>(i, j) * dest_x.at<uchar>(i, j)) + (dest_y.at<uchar>(i, j) * dest_y.at<uchar>(i, j)));
			if (dest_y.at<uchar>(i, j) != 0)
				direction.at<uchar>(i, j) = (uchar)atan(dest_x.at<uchar>(i, j) / dest_y.at<uchar>(i, j));
			else
				direction.at<uchar>(i, j) = 0;
		}

	Mat binarizarea = module.clone();
	int tresh = 128;

	for (int i = 0; i < module.rows; i++)
		for (int j = 0; j < module.cols; j++)
			if (module.at<uchar>(i, j) < 128)
				binarizarea.at<uchar>(i, j) = 0;
			else
				binarizarea.at<uchar>(i, j) = 255;

	imshow("Original", src);
	imshow("Module", module);
	waitKey(0);
}
void RobertsM()
{
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	Mat dest_x = src.clone();
	Mat dest_y = src.clone();


	int Hx[2][2] = { { 1, 0 },
					 { 0, -1 } 
	};

	int Hy[2][2] = { { 0, -1 },
					 { 1, 0 } 
	};

	
	int k = 1;

	for (int y = k; y < src.rows - k; y++)
	{
		for (int x = k; x < src.cols - k; x++)
		{
			int aux1 = 0, aux2 = 0;
			for (int i = -k; i < k; i++)
			{
				for (int j = -k; j < k; j++)
				{
					aux1 += src.at<uchar>(x + j, y + i) * Hx[j + k][i + k];
					aux2 += src.at<uchar>(x + j, y + i) * Hy[j + k][i + k];
				}

				if (aux1 < 0)
					dest_x.at<uchar>(x, y) = 0;
				else if (aux1 > 255)
					dest_x.at<uchar>(x, y) = 255;
				else
					dest_x.at<uchar>(x, y) = aux1;

				if (aux2 < 0)
					dest_y.at<uchar>(x, y) = 0;
				else if (aux2 > 255)
					dest_y.at<uchar>(x, y) = 255;
				else
					dest_y.at<uchar>(x, y) = aux2;

			}
		}
	}

	Mat module = src.clone();
	Mat direction = src.clone();

	for (int i = 1; i < src.rows - 1; i++)
		for (int j = 1; j < src.cols - 1; j++)
		{
			module.at<uchar>(i, j) = (uchar)sqrt((dest_x.at<uchar>(i, j) * dest_x.at<uchar>(i, j)) + (dest_y.at<uchar>(i, j) * dest_y.at<uchar>(i, j)));
			if (dest_y.at<uchar>(i, j) != 0)
				direction.at<uchar>(i, j) = (uchar)atan(dest_x.at<uchar>(i, j) / dest_y.at<uchar>(i, j));
			else
				direction.at<uchar>(i, j) = 0;
		}

	Mat binarizarea = module.clone();
	int tresh = 128;

	for (int i = 0; i < module.rows; i++)
		for (int j = 0; j < module.cols; j++)
			if (module.at<uchar>(i, j) < 128)
				binarizarea.at<uchar>(i, j) = 0;
			else
				binarizarea.at<uchar>(i, j) = 255;

	

	imshow("Original", src);
	imshow("Module", module);
	
	waitKey(0);
}

/////////////////////////////Lab 10/////////////////////////////////////////
void filtruMedian(Mat img, int w) {
	double tick = (double)getTickCount();
	Mat dst = img.clone();

	int k = w / 2;
	std::vector <int> elem = {};
	//parcurgem  imaginea fara primele si ultimele  w/2 randuri si coloane ca sa incapa kernelul
	for (int y = k; y < img.rows - k; y++) {
		for (int x = k; x < img.cols - k; x++) {

			//parcurgem de la marginea inferioara la cea superioara

			for (int i = -k; i <= k; i++) {
				for (int j = -k; j <= k; j++) {
					//adaugam  elementele vecine cu pixelul curent
					elem.push_back(img.at<uchar>(y + i, x + j));
				
				}
			}
			//sortam vectorul
			std::sort(elem.begin(), elem.end());
			//inlocuim cu eleentul median
			dst.at<uchar>(y, x) = elem[pow(w, 2) / 2];
			//golim vectorul ca sa putem merge cu fereasta prin toata matricea
			elem.clear();
		}
	}

	imshow("Imaginea Rezultata", dst);
	tick = ((double)getTickCount() - tick) / getTickFrequency();
	
	printf("Timpul = %.f ms:\n", tick * 1000);
	waitKey(0);


}
Mat filtruMedian2(Mat img, int w) {
	double tick = (double)getTickCount();
	Mat dst = img.clone();

	int k = w / 2;
	std::vector <int> elem = {};
	//parcurgem  imaginea fara primele si ultimele  w/2 randuri si coloane
	for (int y = k; y < img.rows - k; y++) {
		for (int x = k; x < img.cols - k; x++) {

			//parcurgem de la marginea inferioara la cea superioara

			for (int i = -k; i <= k; i++) {
				for (int j = -k; j <= k; j++) {
					//adaugam  elementele vecine cu pixelul curent
					elem.push_back(img.at<uchar>(y + i, x + j));

				}
			}
			//sortam vectorul
			std::sort(elem.begin(), elem.end());
			//inlocuim cu eleentul median
			dst.at<uchar>(y, x) = elem[pow(w, 2) / 2];
			//golim vectorul ca sa putem merge cu fereasta prin toata matricea
			elem.clear();
		}
	}

	return dst;


}
void filtruGaussian(Mat img, int w) {
	double tick = (double)getTickCount(); 
	Mat dst = img.clone();
	//sigma este egala  cu dimensiunea w/6
	float sigma = (float)w / 6;
	int k = w / 2;
	//G -nucleul este o matrice cu dimensiuni variabile 
	Mat G(w, w, CV_32F);

	for (int x = 0; x < w; x++) {
		for (int y = 0; y < w; y++) {
			//calculam formula 10.5 din lab 
			G.at<float>(x, y) = 1.0 / (2 * PI*pow(sigma,2)) * exp(-((pow((x-2),2) + pow((y-2),2) / (2 * pow(sigma,2)))));
		}
	}
	//parcurgerile se fac ca si la filtrul median
	for (int x = k; x < img.rows - k; x++)
		for (int y = k; y < img.cols - k; y++)
		{
			int aux = 0;
			for (int i = -k; i <= k; i++)
				for (int j = -k; j <= k; j++)
				{//adunam rezultatul inmultiri fiecarui element din G cu fiecare element din imaginea initiala
					aux += img.at<uchar>(x + j, y + i)* G.at<float>(i+k,j+k);//adunam la x si  y  ,i si j  ca ne ne asiguram ca parcurgem toata imaginea
				}

			dst.at<uchar>(x, y) = aux;
		}
	imshow("Rezultat", dst);
	tick = ((double)getTickCount() - tick) / getTickFrequency();
	
	printf("Timpul = %.f ms:\n", tick * 1000);
	waitKey(0);
}
Mat filtruGaussian2(Mat img, int w) {
	double tick = (double)getTickCount();
	Mat dst = img.clone();
	//sigma este egala  cu dimensiunea w/6
	float sigma = (float)w / 6;
	int k = w / 2;
	//G -nucleul este o matrice cu dimensiuni variabile 
	Mat G(w, w, CV_32F);

	for (int x = 0; x < w; x++) {
		for (int y = 0; y < w; y++) {
			//calculam formula 10.5 din lab 
			G.at<float>(x, y) = 1.0 / (2 * PI*pow(sigma, 2)) * exp(-((pow((x - 2), 2) + pow((y - 2), 2) / (2 * pow(sigma, 2)))));
		}
	}
	//parcurgerile se fac ca si la filtrul median
	for (int x = k; x < img.rows - k; x++)
		for (int y = k; y < img.cols - k; y++)
		{
			int aux = 0;
			for (int i = -k; i <= k; i++)
				for (int j = -k; j <= k; j++)
				{//adunam rezultatul inmultiri fiecarui element din G cu fiecare element din imaginea initiala
					aux += img.at<uchar>(x + j, y + i)* G.at<float>(i + k, j + k);//adunam la x si  y  ,i si j  ca ne ne asiguram ca parcurgem toata imaginea
				}

			dst.at<uchar>(x, y) = aux;
		}
	return dst;
}


//////////////////////////Lab 8///////////////////////////////////////
void showHistogram(const std::string& name, int* hist, const int  hist_cols, const int hist_height)
{
	Mat imgHist(hist_height, hist_cols, CV_8UC3, CV_RGB(255, 255, 255)); // constructs a white image

	//computes histogram maximum
	int max_hist = 0;
	for (int i = 0; i < hist_cols; i++)
		if (hist[i] > max_hist)
			max_hist = hist[i];
	double scale = 1.0;
	scale = (double)hist_height / max_hist;
	int baseline = hist_height - 1;

	for (int x = 0; x < hist_cols; x++) {
		Point p1 = Point(x, baseline);
		Point p2 = Point(x, baseline - cvRound(hist[x] * scale));
		line(imgHist, p1, p2, CV_RGB(255, 0, 255)); // histogram bins colored in magenta
	}

	imshow(name, imgHist);
}
void computeHistogram(Mat img)
{
	int histogramValues[256];

	for (int i = 0; i < 256; i++)
		histogramValues[i] = 0;

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			histogramValues[img.at<uchar>(i, j)]++;

	showHistogram("Histogram", histogramValues, 255, 200);
	waitKey(0);
}

void media() {
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	imshow("Imaginea initiala", img);


	float avr = 0;
	//M este nr pixelilor din imaginea
	int M = img.rows * img.cols;

	//parcurgem imaginea
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			//calculam suma pixelilor
			avr += img.at<uchar>(i, j);
	//calculam  media
	avr =avr/ M;

	std::cout << "Valoarea medie a nivelurilor de intensistate: " << avr << std::endl;
	computeHistogram(img);


}


void deviatia_standard() {
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	imshow("Imaginea initiala", img);

	int M = img.rows * img.cols;

	float avr = 0;
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			avr += img.at<uchar>(i, j);

	avr /= M;
	//am recalculat media

	float intensitatea = avr;
	float deviatia= 0, x;

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{  //calculam (I(i,j)-u)^2 
			x = pow(img.at<uchar>(i, j) - intensitatea, 2);
			deviatia += x;
		}
	//impartim cu M
	deviatia /= M;
	//conform formulei deviatia este radical din valoarea obtinuta
	deviatia = sqrt(deviatia);

	printf("Deviatia standard %f",deviatia);
	computeHistogram(img); 
}
void binarizareGlobala()
{
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	imshow("Imagine originala", img);

	//calculam histograma
	int histogramValues[256];

	for (int i = 0; i < 256; i++)
		histogramValues[i] = 0;

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			histogramValues[img.at<uchar>(i, j)]++;

	showHistogram("Histograma", histogramValues, 255, 200);

	int iMin = 256, iMax = 0;

	//gasesc intensitatea minima
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			if (img.at<uchar>(i, j) < iMin)
				iMin = img.at<uchar>(i, j);
	//gasesc intensitatea maxima
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			if (img.at<uchar>(i, j) > iMax)
				iMax = img.at<uchar>(i, j);

	//valoarea initiala pt T
	int T = (iMin + iMax) / 2;
	int T1 = 0;
	float error = 0.1f;

	//repetam pasii pana cand T -T1 < error (error e o val subunitara)
	while ((T - T1) >= error)
	{
		int G1h = 0, G2h = 0;
		float G1 = 0, G2 = 0;

		for (int f = iMin; f <= T; f++)
			G1h += histogramValues[f];

		for (int f = T + 1; f <= iMax; f++)
			G2h += histogramValues[f];

		for (int f = iMin; f <= T; f++)
			G1 += (f * histogramValues[f]);

		for (int f = T + 1; f <= iMax; f++)
			G2 += (f * histogramValues[f]);

		//calculam mediile G1 si G2 folosind histograma initiala
		G1 /= (float)G1h;
		G2 /= (float)G2h;

		T1 = T;
		//actualizarea pragului
		T = (int)(G1 + G2) / 2;
	}
	Mat rez(img.rows, img.cols, CV_8UC1);
	//binarizare
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			if (img.at<uchar>(i, j) < T)
				rez.at<uchar>(i, j) = 0;
			else
				rez.at<uchar>(i, j) = 255;
		}

	imshow("Imaginea Rezultat", rez);
	waitKey(0);
}
void negativul()
{
	char fname[MAX_PATH];
	openFileDlg(fname);
	Mat img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	imshow("Imaginea originala", img);

	int histogramValues[256];

	for (int i = 0; i < 256; i++)
		histogramValues[i] = 0;

	//calculam histograma imagini initiale
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			histogramValues[img.at<uchar>(i, j)]++;

	showHistogram("Histograma imaginii initiale", histogramValues, 255, 200);
	//creeaz imaginea rezultat 
	Mat rez(img.rows, img.cols, CV_8UC1);

	//calculez negativul imaginii initiale
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			rez.at<uchar>(i, j) = 255 - img.at<uchar>(i, j);

	imshow("Imaginea negativa", rez);

	int newHistogram[256];
	for (int i = 0; i < 256; i++)
		newHistogram[i] = 0;

	//calculez hisograma imagini negative
	for (int i = 0; i < rez.rows; i++)
		for (int j = 0; j < rez.cols; j++)
			newHistogram[rez.at<uchar>(i, j)]++;

	showHistogram("Histograma negativului imaginii", newHistogram, 255, 200);
	waitKey(0);
}
void corectieG()
{
	char fname[MAX_PATH];
	openFileDlg(fname);

	Mat img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	imshow("Imagine initiala", img);

	int histogramValues[256];

	for (int i = 0; i < 256; i++)
		histogramValues[i] = 0;
	//calculam hisograma imagini initiale
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
			histogramValues[img.at<uchar>(i, j)]++;

	showHistogram("Histograma imaginii initiale", histogramValues, 255, 200);

	Mat rez(img.rows, img.cols, CV_8UC1);
	//citim gama
	float gamma;
	printf("Introduceti gama:");
	scanf("%f", &gamma);

	//pentru fiecare pixel din imagine calculam gout ca find L* (gin/L)^gamma
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			rez.at<uchar>(i, j) = (uchar)(255 * pow(img.at<uchar>(i, j) / (float)255, gamma));

			if (rez.at<uchar>(i, j) < 0)
				rez.at<uchar>(i, j) = 0;

			if (rez.at<uchar>(i, j) > 255)
				rez.at<uchar>(i, j) = 255;
		}

	int newHistogramValues[256];

	for (int i = 0; i < 256; i++)
		newHistogramValues[i] = 0;
	//calculam histograma imagini dupa aplicarea corectiei
	for (int i = 0; i < rez.rows; i++)
		for (int j = 0; j < rez.cols; j++)
			newHistogramValues[rez.at<uchar>(i, j)]++;

	showHistogram("Histograma dupa aplicarea Gamma", newHistogramValues, 255, 200);
	imshow("Imaginea Rezultat", rez);
	waitKey(0);
}
//////////////////////////Lab 7///////////////////////////////////////
Mat dilatare(Mat img,int n) {

	Mat aux=img.clone();//clonam matricea
	
	int dj[] = { 1, 1, 0, -1, -1, -1, 0, 1 };//
	int di[] = { 0, -1, -1, -1, 0, 1, 1, 1 };//facem vectori pentru vecinatate de 8

	int t;

	for (int i = 1; i < img.rows-1 ; i++)//parcurgem matricea de la linia 2 pana la penultima linie 
		for (int j = 1; j < img.cols-1 ;j++)//coloanele la fel
	
			if(img.at<uchar>(i,j)==0){//testam daca pixelul este negru(pixel obj)

				for (t = 0; t < 8;t++)
				{
					aux.at<uchar>(i + di[t], j + dj[t]) = 0; // daca e negru facem toti vecini lui pixeli obj
				}
			}
	if (n == 1)
		return aux;//returnam imaginea dupa o singura dilatarea
	else
	return	dilatare(aux, n - 1);//returnam imaginea dupa n dilatari;
}
Mat erodare(Mat img, int n) {

	Mat aux = Mat(img.rows, img.cols, CV_8UC1);///
	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.rows; j++)//creaz o matrice pentru a stoca imaginea initiala si initializez toti pixeli la alb 
		{
			aux.at<uchar>(i, j) = 255;//
		}
	int t;
	int dj[] = { 1, 1, 0, -1, -1, -1, 0, 1 };//
	int di[] = { 0, -1, -1, -1, 0, 1, 1, 1 };// vectori pentru vecinatate de 8

	for (int i = 1; i < img.rows - 1; i++)//parcurgem matricea de la linia 2 pana la penultima linie 
		for (int j = 1; j < img.cols - 1; j++)//liniile la fel

			if (img.at<uchar>(i, j) == 0) {//daca am gasit un pixel obiect

				bool ok = true;//presupunem totca toti vecini sunt albi
				for (t = 0; t < 8; t++) {
					if (img.at<uchar>(i + di[t], j + dj[t]) != 0)
					{	
						ok = false;
						break;
					}
					
					if (ok)// daca presupunearea este adevarata desenez pixelul
						aux.at<uchar>(i, j) = 0;
				}
			}
	if (n == 1)
		return aux;// img dupa o erodare
	else
		return erodare(aux, n - 1);//img dupa n erodari

}
//Deschiderea=consta intr-o eroziune urmata de o dilatare;
Mat deschidere(Mat img) { 

	Mat aux = erodare(img, 1);
	return dilatare(aux, 1);
}
//Inchiderea = consta intr - o dilatare urmata de o eroziune;

Mat inchidere(Mat img) { 
	
	Mat aux = dilatare(img, 1);
	return erodare(aux, 1);
}

Mat extragere_contur(Mat frame) {
	//declar imaginea initiala
	
	Mat img = frame;
	imshow("Imagine initiala", img);

	//matricea aux este imaginea initiala in urma unei erodari
	Mat aux = erodare(img, 1);

	//creez o matice rezultat pe care o initializez la valoare 255 (alb)
	Mat rez = Mat(img.rows, img.cols, CV_8UC1);

	for(int i=0;i<img.rows;i++)
		for (int j = 0; j < img.rows; j++)
		{
			rez.at<uchar>(i, j) = 255;
		}
	//parcurgem imaginea initiala
	for (int i = 1; i < img.rows - 1; i++)
		for (int j = 1; j < img.cols - 1; j++)
			//daca gasimun pixel obiect si acesta in urma erodari in matrice aux este diferit insemna ca este la margine
			if (img.at<uchar>(i, j) == 0 && aux.at<uchar>(i, j) != 0)
				rez.at<uchar>(i, j) = 0; //il facem in rez pixel de contur

	//imshow("Extragerea Conturului", rez);
	//waitKey(0);

	return rez;
}

void umplerea_regiunilor() {
	char fname[MAX_PATH];// declar imaginea initiala
	openFileDlg(fname);
	Mat img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
	Mat rez = Mat(img.rows, img.cols, CV_8UC1);

	for (int i = 0; i < img.rows; i++)//initializez matricea auxiliara la alb
		for (int j = 0; j < img.rows; j++)
		{
			rez.at<uchar>(i, j) = 255;
		}

	rez.at<uchar>(img.rows / 2, img.cols / 2) = 0;

	Mat aux; 
	bool ok;
	int di[4] = { 0, -1, 0, 1 };//creez vectori pentru vecinatate de 4
	int dj[4] = { 1, 0, -1, 0 };//

	do {
		rez.copyTo(aux);
		
		ok = true;
		for (int i = 1; i < rez.rows - 1; i++)//parcurgem matricea
			for (int j = 1; j < rez.cols - 1; j++)
				if (rez.at<uchar>(i, j) == 0)
				{
					for (int k = 0; k < 4; k++)
						if (aux.at<uchar>(i + di[k], j + dj[k]) != 0 && img.at<uchar>(i + di[k], j + dj[k]) != 0) {// verificam daca pixeli din imaginea 
																													//initiala si cei din aux sunt la albi
							aux.at<uchar>(i + di[k], j + dj[k]) = 0;//in aux facem acel pixel negru
							ok = false;
						}

				}
		aux.copyTo(rez);
		
	} while (!ok);

	for (int i = 1; i < img.rows - 1; i++)
		for (int j = 1; j < img.cols - 1; j++)//parcurgem imaginea si coloram in rezultat obiectul
			if (img.at<uchar>(i, j) == 0)
				rez.at<uchar>(i, j) = 0;

	imshow("Normal image", img);
	imshow("Umplere", rez);
	waitKey(0);
}





////////////////////lab6//////////////////////////////////////
typedef struct {
	int i, j;
	int c;
} Point1;



void contur() {
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE); 
		imshow("img initiala", src);
		Mat dst = Mat(src.size(), CV_8UC1); 

		vector <Point1> contur;

		bool ok = false;  
		int i0, j0;

		for (int i = 0; i < src.rows && !ok; i++) 
			for (int j = 0; j < src.cols && !ok; j++)   
				if (src.at<uchar>(i, j) == 0) {    //gasim primul pixel obiect
					i0 = i;
					j0 = j;
					ok = true;
				}
			
		
		//folosesc vecinatate de  8
		int dj[] = { 1, 1, 0, -1, -1, -1, 0, 1 };    
		int di[] = { 0, -1, -1, -1, 0, 1, 1, 1 };    

		//salvez primul primul punct
		contur.push_back(Point1{ i0, j0, 7 });

		bool ok2 = false;

		int x = i0;  
		int y = j0;
		int dir = 7;  

		while (!ok2) {
			if (dir % 2 == 0)   
				dir = (dir + 7) % 8; // par
			else 
				dir = (dir + 6) % 8;  // impar
			

			while (src.at<uchar>(x + di[dir], y + dj[dir]) != 0) {   
				dir = dir + 1;               
				if (dir == 8)              
					dir = 0;				
			}

			contur.push_back(Point1{ x + di[dir],  y + dj[dir], dir });

			x += di[dir];
			y += dj[dir];

			int n = contur.size();  
			if (n > 2 && contur.at(0).i == contur.at(n - 2).i && contur.at(0).j == contur.at(n - 2).j && contur.at(1).i == contur.at(n - 1).i && contur.at(1).j == contur.at(n - 1).j) 
				ok2 = true;  //ne oprim cand primul punct este egal cu al doilea
			
		}

		
		for (int i = 0; i < contur.size(); i++) {  
			dst.at<uchar>(contur.at(i).i, contur.at(i).j) = 0;
			int v = 7;  
			if (i > 0)  
				v = contur.at(i).c - contur.at(i - 1).c;  //calculam diferenta intre pozitia urmatoare si pozitia curenta
			if (v < 0)  
				v += 8;   
			printf("%i ( %i , %i ) %i , %i \n", i, contur.at(i).i, contur.at(i).j, contur.at(i).c, v);  
			
		}                                 
		

		imshow("contur imag init", dst);  
		waitKey();
	}
}

void reconstruct() {
	FILE *f = fopen("reconstruct.txt", "r");  
											
	int x, y, l, dir;

	fscanf(f, "%d %d", &x, &y); 
	fscanf(f, "%d", &l);

	Mat dst = Mat(1000, 1000, CV_LOAD_IMAGE_GRAYSCALE); 

	int dj[] = { 1, 1, 0, -1, -1, -1, 0, 1 };   
	int di[] = { 0, -1, -1, -1, 0, 1, 1, 1 };

	for (int i = 0; i < l; i++) {
		dst.at<uchar>(x, y) = 0;
		fscanf(f, "%d", &dir);
		x = x + di[dir]; 
		y = y + dj[dir];

	}
	imshow("reconstructie imagine ", dst);
	waitKey();
	fclose(f);
}

////////////////////lab5 ////////////////////////////////////
void lab5_ex1si2() {
    Mat src = imread("Images/letters.bmp", CV_LOAD_IMAGE_GRAYSCALE);
    Mat labels = Mat::zeros(src.rows, src.cols, CV_32SC1); 
    Mat dst = Mat::zeros(src.rows, src.cols, CV_8UC3); 
    int label = 0;
    std::queue<Point> Q;
    
    int di[8] = { -1,  0, 1, 0, 1, -1, 1 , -1 };
    int dj[8] = { 0, -1, 0, 1, 1,  1 , -1 , -1 };
    int ni = 0; 
    int nj = 0; 

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            if (src.at<uchar>(i, j) == 0 && labels.at<int>(i, j) == 0) {
                label++;
                labels.at<int>(i, j) = label;
                Q.push({ i, j });
                while (!Q.empty()) {
                    Point p = Q.front();
                    Q.pop();
                    for (int k = 0; k < 8; k++) {
                        ni = p.x + di[k];
                        nj = p.y + dj[k];
                        if (!(ni < 0 || ni >= src.rows || nj < 0 || nj >= src.cols)
                            && labels.at<int>(ni, nj) == 0 && src.at<uchar>(ni, nj) == 0) {
                            labels.at<int>(ni, nj) = label;
                            Q.push({ ni, nj });

                        }
                    }
                }
            }
        }
    }

    vector<Vec3b> culori = vector<Vec3b>(label);
    std::default_random_engine gen;
    std::uniform_int_distribution<int> d(0, 255);
    for (int i = 0; i < label; i++) {
        uchar r = d(gen);
        uchar g = d(gen);
        uchar b = d(gen);
        culori.at(i) = Vec3b(r, g, b);
    }
    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            if (labels.at<int>(i, j) > 0) {
                dst.at<Vec3b>(i, j) = culori.at(labels.at<int>(i, j) -1);
            }
        }
    }
    imshow("Etichetare", dst);
    waitKey();
}
void lab5_ex3() {

    char fname[MAX_PATH];
    openFileDlg(fname);
    Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);

    waitKey();
    Mat labels = Mat::zeros(src.rows, src.cols, CV_32SC1);
    Mat dstP = Mat::zeros(src.rows, src.cols, CV_8UC3);
    Mat dstF = Mat::zeros(src.rows, src.cols, CV_8UC3);

    int di[8] = { -1, 0, 1, 0, 1, -1, 1, -1 };
    int dj[8] = { 0, -1, 0, 1, 1, 1, -1, -1 };
    int label = 1;

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            if (src.at<uchar>(i, j) != 255) {
                labels.at<int>(i, j) = label;
                label++;
            }
            else
                labels.at<int>(i, j) = 0;
        }
    }

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            if (labels.at<int>(i, j) != 0) {
                int smallest = labels.at<int>(i, j);
                for (int k = 0; k < 8; k++) {
                    if (smallest > labels.at<int>(i + di[k], j + dj[k]) && labels.at<int>(i + di[k], j + dj[k]) != 0) {
                        smallest = labels.at<int>(i + di[k], j + dj[k]);
                    }
                }
                labels.at<int>(i, j) = smallest;
            }
        }
    }
    vector<Vec3b> culori = vector<Vec3b>(label);
    std::default_random_engine gen;
    std::uniform_int_distribution<int> d(0, 255);
    for (int i = 0; i < label; i++) {
        uchar r = d(gen);
        uchar g = d(gen);
        uchar b = d(gen);
        culori.at(i) = Vec3b(r, g, b);
    }

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            if (labels.at<int>(i, j) > 0) {
                dstP.at<Vec3b>(i, j) = culori.at(labels.at<int>(i, j) - 1);
            }
            else {
                dstP.at<Vec3b>(i, j) = Vec3b(255, 255, 255);
            }
        }
    }
    for (int i = src.rows - 1; i >= 0; i--) {
        for (int j = src.cols - 1; j >= 0; j--) {
            if (labels.at<int>(i, j) != 0) {
                int smallest = labels.at<int>(i, j);
                for (int k = 0; k < 8; k++) {
                    if (smallest > labels.at<int>(i + di[k], j + dj[k]) && labels.at<int>(i + di[k], j + dj[k]) != 0) {
                        smallest = labels.at<int>(i + di[k], j + dj[k]);
                    }
                }
                labels.at<int>(i, j) = smallest;
            }
        }
    }

    for (int i = 0; i < label; i++) {
        uchar r = d(gen);
        uchar g = d(gen);
        uchar b = d(gen);
        culori.at(i) = Vec3b(r, g, b);
    }

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            if (labels.at<int>(i, j) > 0) {
                dstF.at<Vec3b>(i, j) = culori.at(labels.at<int>(i, j) - 1);
            }
            else {
                dstF.at<Vec3b>(i, j) = Vec3b(255, 255, 255);
            }
        }
    }
    imshow("partial", dstP);
    imshow("final", dstF);
    waitKey();

}
///////////////////////lab4///////////////////////////////
void geometricComputations(int event, int x, int y, int flags, void* param) {

	Mat* src = (Mat*)param;
	if (event == CV_EVENT_LBUTTONDOWN)
	{
		printf("x = %d, y = %d\n", x, y);
		int red = (*src).at<Vec3b>(y, x)[2];
		int green = (*src).at<Vec3b>(y, x)[1];
		int blue = (*src).at<Vec3b>(y, x)[0];

		// Aria
		int aria = 0;

		for (int i = 0; i < (*src).rows; i++) {
			for (int j = 0; j < (*src).cols; j++) {
				if ((*src).at<Vec3b>(i, j)[2] == red &&
					(*src).at<Vec3b>(i, j)[1] == green &&
					(*src).at<Vec3b>(i, j)[0] == blue) {
					aria++;
				}
			}
		}

		printf("Aria: %d\n", aria);

		//centru de greutate
		int pointCount = 0, x_value = 0, y_value = 0;

		for (int i = 0; i < (*src).rows; i++) {
			for (int j = 0; j < (*src).cols; j++) {
				if ((*src).at<Vec3b>(i, j)[2] == red &&
					(*src).at<Vec3b>(i, j)[1] == green &&
					(*src).at<Vec3b>(i, j)[0] == blue) {
					pointCount++;
					x_value += j;
					y_value += i;
				}
			}
		}

		x_value /= pointCount;
		y_value /= pointCount;
		Point p = Point(x_value, y_value);
		printf("Mass Center(%d, %d)\n", x_value, y_value);

		line(Mat(*src), p, p, CV_RGB(0, 0, 0), 5);
		imshow("My Window", (*src));

		//perimetru
		int Per = 0;

		for (int i = 1; i < (*src).rows - 1; i++) {
			for (int j = 1; j < (*src).cols - 1; j++) {

				if ((*src).at<uchar>(i, j) == 0) {
					if (((*src).at<uchar>(i - 1, j) != 0) || ((*src).at<uchar>(i - 1, j - 1) != 0) || ((*src).at<uchar>(i, j - 1) != 0) || ((*src).at<uchar>(i + 1, j - 1) != 0) || ((*src).at<uchar>(i - 1, j + 1) != 0) || ((*src).at<uchar>(i + 1, j + 1) != 0) || ((*src).at<uchar>(i, j + 1) != 0) || ((*src).at<uchar>(i + 1, j) != 0))
					{
						Per++;
					}
				}


			}
		}

		printf("Perimeter %d \n", Per);
		//compute projection image
		Mat dest((*src).rows, (*src).cols, CV_8UC1);
		int x_vec[500] = { 0 };
		int y_vec[500] = { 0 };
		for (int i = 0; i < (*src).rows; i++) {
			for (int j = 0; j < (*src).cols; j++) {
				if ((*src).at<Vec3b>(i, j)[2] == red &&
					(*src).at<Vec3b>(i, j)[1] == green &&
					(*src).at<Vec3b>(i, j)[0] == blue) {
					x_vec[i]++;
					y_vec[j]++;
				}
			}
		}
		for (int k = 0; k < dest.cols; k++) {
			line(dest, Point(k, 0), Point(k, y_vec[k]), 5);
		}
		for (int k = 0; k < dest.rows; k++) {
			line(dest, Point(k, 0), Point(k, x_vec[k]), 5);
		}


		//factorul de subtiere
		float T = 4 * PI* ((float)aria / (Per*Per));
		printf("Factor subtiere %f \n", T);

		//elongatie
		Mat img1;
		img1 = (*src).clone();
		int Cmin = img1.cols, Cmax = 0, Rmin = img1.rows, Rmax = 0;
		for (int i = 0; i < img1.rows; i++) {
			for (int j = 0; j < img1.cols; j++) {
				if (img1.at<uchar>(i, j) == 0) {
					if (i < Rmin)
						Rmin = i;
					if (i > Rmax)
						Rmax = i;
					if (j < Cmin)
						Cmin = j;
					if (j > Cmax)
						Cmax = j;
				}
			}
		}
		float elongatie = (Cmax - Cmin + 1.0) / (Rmax - Rmin + 1);
		printf("Elongatia %f \n", elongatie);

	}

}


void computeAria()
{
	Mat src;
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		src = imread(fname);

		namedWindow("My Window", 1);

		setMouseCallback("My Window", geometricComputations, &src);

		imshow("My Window", src);
		waitKey(0);
	}
}

void geometricProperties(int event, int x, int y, int flags, void* param)
{

	Mat* src = (Mat*)param;
	if (event == CV_EVENT_LBUTTONDBLCLK)
	{
		printf("Pos(x,y): %d,%d  Color(RGB): %d,%d,%d\n", x, y, (int)(*src).at<Vec3b>(y, x)[2], (int)(*src).at<Vec3b>(y, x)[1],
			(int)(*src).at<Vec3b>(y, x)[0]);


		float b, g, r;

		b = (*src).at<Vec3b>(y, x)[0];
		g = (*src).at<Vec3b>(y, x)[1];
		r = (*src).at<Vec3b>(y, x)[2];

		Vec3b pixel, myColor;

		myColor[0] = b;
		myColor[1] = g;
		myColor[2] = r;

		int area = 0, rCenter = 0, cCenter = 0;

		float top = 0.0, bottom = 0.0, elongation;
		float perimeter = 0.0;

		for (int i = 0; i < (*src).rows; i++) {
			for (int j = 0; j < (*src).cols; j++) {
				pixel = (*src).at<Vec3b>(i, j);


				if (pixel[0] == b && pixel[1] == g && pixel[2] == r) {

					area += 1;


					rCenter += i;
					cCenter += j;
				}
			}
		}

		rCenter = rCenter / area;
		cCenter = cCenter / area;

		Point p = Point(rCenter, cCenter);

		bool perimeterPixel;
		float thiness, aspectRatio;

		float rmin = src->rows, rmax = 0, cmin = src->cols, cmax = 0;

		for (int i = 0; i < (*src).rows; i++) {
			for (int j = 0; j < (*src).cols; j++) {
				pixel = (*src).at<Vec3b>(i, j);


				if (pixel[0] == b && pixel[1] == g && pixel[2] == r) {

					if (i < rmin)
						rmin = i;
					if (i > rmax)
						rmax = i;
					if (j < cmin)
						cmin = j;
					if (j > cmax)
						cmax = j;


					top += ((i - rCenter) * (j - cCenter));
					bottom += (((j - cCenter) * (j - cCenter)) - ((i - rCenter) * (i - rCenter)));


					perimeterPixel = false;
					if (i - 1 >= 0 && j - 1 >= 0 && src->at<Vec3b>(i - 1, j - 1) != myColor)
						perimeterPixel = true;

					if (i - 1 >= 0 && src->at<Vec3b>(i - 1, j) != myColor)
						perimeterPixel = true;

					if (i - 1 >= 0 && j + 1 < src->cols && src->at<Vec3b>(i - 1, j + 1) != myColor)
						perimeterPixel = true;

					if (j - 1 >= 0 && src->at<Vec3b>(i, j - 1) != myColor)
						perimeterPixel = true;

					if (j + 1 >= 0 && src->at<Vec3b>(i, j + 1) != myColor)
						perimeterPixel = true;

					if (i + 1 < src->rows && j - 1 >= 0 && src->at<Vec3b>(i + 1, j - 1) != myColor)
						perimeterPixel = true;

					if (i + 1 < src->rows && src->at<Vec3b>(i + 1, j) != myColor)
						perimeterPixel = true;

					if (i + 1 < src->rows && j + 1 < src->cols && src->at<Vec3b>(i + 1, j + 1) != myColor)
						perimeterPixel = true;

					if (perimeterPixel)
						perimeter += 1;

				}
			}
		}

		aspectRatio = (cmax - cmin + 1) / (rmax - rmin + 1);

		top *= 2;
		float fi;
		elongation = (atan2(top, bottom)) / 2.0f;
		fi = elongation;
		elongation = elongation * 180 / CV_PI;

		if (elongation < 0)
			elongation = 180 + elongation;

		perimeter = (PI / 4) * perimeter;

		thiness = (4 * PI)* (area / (perimeter * perimeter));


		line((*src), Point((int)cmin, (int)(rCenter + tan(fi)* (cmin - cCenter))), Point((int)cmax, (int)(rCenter + tan(fi) * (cmax - cCenter))), CV_RGB(130, 70, 240));

		int counter = 0;

		//p1
		for (int i = 0; i < (*src).rows; i++) {
			counter = 0;
			for (int j = 0; j < (*src).cols; j++) {
				pixel = (*src).at<Vec3b>(i, j);

				if (pixel[0] == b && pixel[1] == g && pixel[2] == r) {
					counter += 1;
				}
			}
			line((*src), Point(0, i), Point(counter, i), CV_RGB(0, 0, 255));
		}

		//p2
		for (int j = 0; j < (*src).cols; j++) {
			counter = 0;
			for (int i = 0; i < (*src).rows; i++) {
				pixel = (*src).at<Vec3b>(i, j);

				if (pixel[0] == b && pixel[1] == g && pixel[2] == r) {
					counter += 1;
				}
			}
			line((*src), Point(j, (*src).rows), Point(j, (*src).rows - counter), CV_RGB(0, 0, 255));
		}

		printf("The area is: %d\n", area);
		printf("The center of mass is: %d %d\n", rCenter, cCenter);
		printf("The elongation is: %f\n", elongation);
		printf("The perimeter is: %f\n", perimeter);
		printf("The thiness is (factorul de subtiere): %f\n", thiness);
		printf("The aspect ratio is(elongatia): %f\n", aspectRatio);

		imshow("Geom_properties", (*src));
	}
}

void geometricalFeatures() {

	Mat src;

	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		src = imread(fname, CV_LOAD_IMAGE_COLOR);

		namedWindow("My Window", 1);

		setMouseCallback("My Window", geometricProperties, &src);

		imshow("My Window", src);

		waitKey(0);
	}
}




/////////////////////////////////////////////////////////////
//////////////////////////////lab 3////////////////////////
	////6 din 7///
int *histrogramComputation() {
	Mat img = imread("Images/cameraman.bmp", CV_LOAD_IMAGE_GRAYSCALE);
	int *arr = (int*)malloc(255 * sizeof(int));

	int val;

	for (int i = 0; i < 256; i++)
		arr[i] = 0;

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			val = img.at<uchar>(i, j);
			arr[val]++;
		}
	for (int i = 0; i < 256; i++)
		std::printf("val[%d] = %d \n", i, arr[i]);

	return arr;


}


float *FDPcomputation(Mat img) {
	//Mat img = imread("Images/cameraman.bmp", CV_LOAD_IMAGE_GRAYSCALE);
	float M;
	M = img.rows * img.cols;
	float *arr = (float*)malloc(255 * sizeof(float));
	int val;

	for (int i = 0; i < 256; i++)
		arr[i] = 0;

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			val = img.at<uchar>(i, j);
			arr[val]++;
		}


	for (int i = 0; i < 256; i++)
		arr[i] = arr[i] / M;

	return arr;
}



void displayHistogram(const string& name, int *hist, const int hist_cols, const int hist_height) {

	Mat imgHist(hist_height, hist_cols, CV_8UC3, CV_RGB(255, 255, 255));

	int max_hist = 0;
	for (int i = 0; i < hist_cols; i++)
		if (hist[i] > max_hist)
			max_hist = hist[i];
	double scale = 1.0;
	scale = (double)hist_height / max_hist;
	int baseline = hist_height - 1;
	for (int x = 0; x < hist_cols; x++) {
		Point p1 = Point(x, baseline);
		Point p2 = Point(x, baseline - cvRound(hist[x] * scale));
		line(imgHist, p1, p2, CV_RGB(255, 0, 255));
	} imshow("Histograma", imgHist);
	waitKey(0);
}


int *histrogramGivenNumber(int m) {
	Mat img = imread("Images/cameraman.bmp", CV_LOAD_IMAGE_GRAYSCALE);
	int *arr = (int*)malloc(255 / m * sizeof(int));

	int val;

	for (int i = 0; i < 256 / m; i++)
		arr[i] = 0;

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			val = img.at<uchar>(i, j) / m;
			arr[val]++;
		}
	for (int i = 0; i < 256 / m; i++)
		std::printf("val[%d] = %d \n", i, arr[i]);

	return arr;


}

void multilevelThresholdingAlg() {
	Mat img = imread("Images/cameraman.bmp", CV_LOAD_IMAGE_GRAYSCALE);
	float *hist = FDPcomputation(img);

	int max_hist = 0;
	for (int i = 0; i < 256; i++)
		if (hist[i] > max_hist)
			max_hist = hist[i];

	int wh = 5;
	float th = 0.0003;
	float v;
	float sum = 0;
	bool bigger = false;
	vector <int> maxm;

	Mat imgHist(256, 2 * wh + 1, CV_8UC3, CV_RGB(255, 255, 255));

	maxm.push_back(0);
	for (int k = 0 + wh; k < 255 - wh; k++)
	{
		bigger = false;
		for (int i = k - wh; i < k + wh; i++)
		{
			sum = sum + hist[i];
			if (hist[k] > hist[i])
				bigger = true;
		}
		v = sum / (2 * wh + 1);


		if ((hist[k] > (v + th)) && (bigger == true))
			maxm.push_back(k);
	}
	maxm.push_back(255);

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
		}

}

float avg(float *a, int start, int stop) {
	float sum = 0;
	for (int i = start; i <= stop; i++) {
		sum += a[i];
	}
	return (sum / (stop - start + 1));
}

boolean isMaxim(float *a, int wh, int pos) {
	float max = a[pos - wh];
	for (int i = pos - wh + 1; i <= pos + wh; i++) {
		if (a[i] > max)
			max = a[i];
	}
	if (a[pos] == max) {
		return true;
	}
	return false;
}

int getGreyValue(std::vector<int> levels, int val) {
	for (int i = 1; i < levels.size(); i++) {
		if (val <= levels.at(i)) {
			if (val - levels.at(i - 1) < levels.at(i) - val)
				return levels.at(i - 1);
			else
				return levels.at(i);
		}
	}
}


void floydSteinbergAlg() {
	int h[256];
	float p[256];
	std::vector<int> levels;
	levels.push_back(0);
	std::fill_n(h, 256, 0);
	int wh = 5;
	float th = 0.0003f;
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat img;
		img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		for (int i = 0; i < img.rows; i++) {
			for (int j = 0; j < img.cols; j++) {
				h[img.at<uchar>(i, j)]++;
			}
		}
		for (int i = 0; i < 256; i++) {
			p[i] = (float)h[i] / (img.rows * img.cols);
		}
		for (int i = wh; i < 256 - wh; i++) {
			if (isMaxim(p, wh, i) && p[i] > (avg(p, i - wh, i + wh) + th))
			{
				levels.push_back(i);
			}
		}
		levels.push_back(255);
		int h2[256];
		std::fill_n(h2, 256, 0);
		for (int i = 1; i < img.rows - 2; i++) {
			for (int j = 1; j < img.cols - 2; j++) {
				uchar old_value = img.at<uchar>(i, j);
				img.at<uchar>(i, j) = (uchar)getGreyValue(levels, (int)img.at<uchar>(i, j));
				int error = old_value - img.at<uchar>(i, j);
				img.at<uchar>(i, j + 1) = max(min(img.at<uchar>(i, j + 1) + 7 * error / 16, 255), 0);
				img.at<uchar>(i + 1, j - 1) = max(min(img.at<uchar>(i + 1, j - 1) + 3 * error / 16, 255), 0);
				img.at<uchar>(i + 1, j) = max(min(img.at<uchar>(i + 1, j) + 5 * error / 16, 255), 0);
				img.at<uchar>(i + 1, j + 1) = max(min(img.at<uchar>(i + 1, j + 1) + error / 16, 255), 0);
				h2[img.at<uchar>(i, j)]++;
			}
		}
		imshow("result", img);
		//showHistogram("histogram", h2, 256, 200);
		waitKey();
	}
}

/////////////////LAB 2///////////////////////

bool lab2p5() {
	Mat img = imread("Images/Lena_24bits.bmp", -1);
	printf("I=");
	int i;
	scanf("%d", &i);

	printf("J=");
	int j;
	scanf("%d", &j);
	if ((i >= 0) && (j >= 0) && i < img.rows && j < img.cols)
		return true;
	return false;

}
void lab2p4() {

	float r, g, b;
	float M, m, C, V1, S1, H1;


	Mat img = imread("Images/Lena_24bits.bmp", -1);
	Mat H(img.rows, img.cols, CV_8UC1);
	Mat S(img.rows, img.cols, CV_8UC1);
	Mat V(img.rows, img.cols, CV_8UC1);


	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {

			Vec3b pixel = img.at<Vec3b>(i, j);

			r = (float)pixel[2] / 255;
			g = (float)pixel[1] / 255;
			b = (float)pixel[0] / 255;

			m = min(min(r, g), b);

			M = max(max(r, g), b);

			C = M - m;

			V1 = M;

			if (V1 != 0)
			{
				S1 = C / V1;
			}
			else
			{
				S1 = 0;
			}

			if (C != 0)
			{
				if (M == r) H1 = 60 * (g - b) / C;
				if (M == g) H1 = 120 + 60 * (g - b) / C;
				if (M == b) H1 = 240 + 60 * (g - b) / C;
			}
			else {

				H1 = 0;
			}
			if (S1 < 0)
				S1 += 360;

			H1 = H1 * 255 / 360;
			S1 = S1 * 255;
			V1 = V1 * 255;

			H.at<uchar>(i, j) = H1;
			S.at<uchar>(i, j) = S1;
			V.at<uchar>(i, j) = V1;
		}
	}

	imshow(" image1", img);
	imshow(" value", V);
	imshow(" saturation", S);
	imshow(" hue", H);
	waitKey(0);
}
void lab2p3() {
	Mat img = imread("Images/Lena_24bits.bmp", -1);
	Mat img1(img.rows, img.cols, CV_8UC3);

	printf("treashold = ");
	int treashold;
	scanf("%d", &treashold);


	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			Vec3b pixel = img.at<Vec3b>(i, j);
			float gry = (pixel[0] + pixel[1] + pixel[2]) / 3;
			if (gry > treashold) {

				img1.at<Vec3b>(i, j)[0] = 0; //B
				img1.at<Vec3b>(i, j)[1] = 0; //G
				img1.at<Vec3b>(i, j)[2] = 0; //R	
			}
			else
			{

				img1.at<Vec3b>(i, j)[0] = 255; //B
				img1.at<Vec3b>(i, j)[1] = 255; //G
				img1.at<Vec3b>(i, j)[2] = 255; //R	
			}

		}
	}
	imshow(" image1", img);
	imshow(" image", img1);
	waitKey(0);
}

void lab2p22() {

	Mat img = imread("Images/Lena_24bits.bmp", CV_LOAD_IMAGE_COLOR);
	Mat img1(img.rows, img.cols, CV_8UC1);

	int a = 0.2989;
	int b = 0.5870;
	int c = 0.1140;

	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			Vec3b pixel = img.at<Vec3b>(i, j);
			img1.at<uchar>(i, j) = (a*pixel[0] + b * pixel[1] + c * pixel[2]);

		}
	}
	imshow(" image1", img);
	imshow(" image", img1);
	waitKey(0);
}

void lab2p2() {

	Mat img = imread("Images/Lena_24bits.bmp", CV_LOAD_IMAGE_COLOR);
	Mat img1(img.rows, img.cols, CV_8UC1);

	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			Vec3b pixel = img.at<Vec3b>(i, j);
			img1.at<uchar>(i, j) = (pixel[0] + pixel[1] + pixel[2]) / 3;

		}
	}
	imshow(" image1", img);
	imshow(" image", img1);
	waitKey(0);
}

void lab2p1() {


	Mat img = imread("Images/Lena_24bits.bmp", CV_LOAD_IMAGE_COLOR);
	Mat img1(256, 256, CV_8UC3);
	Mat img2(256, 256, CV_8UC3);
	Mat img3(256, 256, CV_8UC3);


	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			Vec3b pixel = img.at<Vec3b>(i, j);

			img1.at<Vec3b>(i, j)[0] = pixel[0]; //B
			img1.at<Vec3b>(i, j)[1] = 0; //G
			img1.at<Vec3b>(i, j)[2] = 0; //R
		}
	}
	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			Vec3b pixel = img.at<Vec3b>(i, j);

			img2.at<Vec3b>(i, j)[0] = 0; //B
			img2.at<Vec3b>(i, j)[1] = pixel[1]; //G
			img2.at<Vec3b>(i, j)[2] = 0; //R
		}
	}
	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			Vec3b pixel = img.at<Vec3b>(i, j);

			img3.at<Vec3b>(i, j)[0] = 0; //B
			img3.at<Vec3b>(i, j)[1] = 0; //G
			img3.at<Vec3b>(i, j)[2] = pixel[2]; //R
		}
	}
	imshow(" image1", img1);
	imshow(" image2", img2);
	imshow(" image3", img3);
	waitKey(0);
}

void creare_img() {
	Mat img(256, 256, CV_8UC3);

	for (int i = 0; i < img.rows / 2; i++) {
		for (int j = 0; j < img.cols / 2; j++) {

			img.at<Vec3b>(i, j)[0] = 255; //B
			img.at<Vec3b>(i, j)[1] = 255; //G
			img.at<Vec3b>(i, j)[2] = 255; //R
		}
	}
	for (int i = img.rows / 2; i < img.rows; i++) {
		for (int j = img.cols / 2; j < img.cols; j++) {

			img.at<Vec3b>(i, j)[0] = 0; //B
			img.at<Vec3b>(i, j)[1] = 255; //G
			img.at<Vec3b>(i, j)[2] = 255; //R
		}
	}
	for (int i = 0; i < img.rows / 2; i++) {
		for (int j = img.cols / 2; j < img.cols; j++) {

			img.at<Vec3b>(i, j)[0] = 0; //B
			img.at<Vec3b>(i, j)[1] = 0; //G
			img.at<Vec3b>(i, j)[2] = 255; //R
		}
	}
	for (int i = img.rows / 2; i < img.rows; i++) {
		for (int j = 0; j < img.cols / 2; j++) {

			img.at<Vec3b>(i, j)[0] = 0; //B
			img.at<Vec3b>(i, j)[1] = 255; //G
			img.at<Vec3b>(i, j)[2] = 0; //R
		}
	}
	imshow("negative image", img);
	waitKey(0);
}
void inv2() {
	float a[9] = { 9.0f, 8.0f, 7.0f, 6.2f, 5.0f, 4.0f, 3.0f, 2.0f, 1.0f };
	Mat M(3, 3, CV_32FC1, a);

	float det = determinant(M);
	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			printf("%.2f \t", (M.at<float>((i + 1) % 3, (j + 1) % 3) * M.at<float>((i + 2) % 3, (j + 2) % 3) - M.at<float>((i + 1) % 3, (j + 2) % 3)*M.at<float>((i + 2) % 3, (j + 1) % 3)) / det);
	printf("\n");
	system("pause");
}
void inv() {
	float a[9] = { 9.0f, 8.0f, 7.0f, 6.2f, 5.0f, 4.0f, 3.0f, 2.0f, 1.0f };
	Mat M(3, 3, CV_32FC1, a);

	std::cout << M.inv() << std::endl;
	waitKey(0);
	system("pause");
}
void negative_image() {
	Mat img = imread("Images/cameraman.bmp", CV_LOAD_IMAGE_GRAYSCALE);
	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {
			img.at<uchar>(i, j) = 255 - img.at<uchar>(i, j);
		}
	}

	imshow("negative image", img);
	waitKey(0);
}
/////////////////////////////////////////////////////////
void testOpenImage()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src;
		src = imread(fname);
		imshow("image",src);
		waitKey();
	}
}

void testOpenImagesFld()
{
	char folderName[MAX_PATH];
	if (openFolderDlg(folderName)==0)
		return;
	char fname[MAX_PATH];
	FileGetter fg(folderName,"bmp");
	while(fg.getNextAbsFile(fname))
	{
		Mat src;
		src = imread(fname);
		imshow(fg.getFoundFileName(),src);
		if (waitKey()==27) //ESC pressed
			break;
	}
}

void testImageOpenAndSave()
{
	Mat src, dst;

	src = imread("Images/Lena_24bits.bmp", CV_LOAD_IMAGE_COLOR);	// Read the image

	if (!src.data)	// Check for invalid input
	{
		printf("Could not open or find the image\n");
		return;
	}

	// Get the image resolution
	Size src_size = Size(src.cols, src.rows);

	// Display window
	const char* WIN_SRC = "Src"; //window for the source image
	namedWindow(WIN_SRC, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_SRC, 0, 0);

	const char* WIN_DST = "Dst"; //window for the destination (processed) image
	namedWindow(WIN_DST, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_DST, src_size.width + 10, 0);

	cvtColor(src, dst, CV_BGR2GRAY); //converts the source image to a grayscale one

	imwrite("Images/Lena_24bits_gray.bmp", dst); //writes the destination to file

	imshow(WIN_SRC, src);
	imshow(WIN_DST, dst);

	printf("Press any key to continue ...\n");
	waitKey(0);
}

void testNegativeImage()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		double t = (double)getTickCount(); // Get the current time [s]
		
		Mat src = imread(fname,CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		Mat dst = Mat(height,width,CV_8UC1);
		// Asa se acceseaaza pixelii individuali pt. o imagine cu 8 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				uchar val = src.at<uchar>(i,j);
				uchar neg = 255 - val;
				dst.at<uchar>(i,j) = neg;
			}
		}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image",src);
		imshow("negative image",dst);
		waitKey();
	}
}

void testParcurgereSimplaDiblookStyle()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
		int height = src.rows;
		int width = src.cols;
		Mat dst = src.clone();

		double t = (double)getTickCount(); // Get the current time [s]

		// the fastest approach using the “diblook style”
		uchar *lpSrc = src.data;
		uchar *lpDst = dst.data;
		int w = (int) src.step; // no dword alignment is done !!!
		for (int i = 0; i<height; i++)
			for (int j = 0; j < width; j++) {
				uchar val = lpSrc[i*w + j];
				lpDst[i*w + j] = 255 - val;
			}

		// Get the current time again and compute the time difference [s]
		t = ((double)getTickCount() - t) / getTickFrequency();
		// Print (in the console window) the processing time in [ms] 
		printf("Time = %.3f [ms]\n", t * 1000);

		imshow("input image",src);
		imshow("negative image",dst);
		waitKey();
	}
}

Mat testColor2Gray(Mat frame)
{
	
		Mat src = frame;

		int height = src.rows;
		int width = src.cols;

		Mat dst = Mat(height,width,CV_8UC1);

		// Asa se acceseaaza pixelii individuali pt. o imagine RGB 24 biti/pixel
		// Varianta ineficienta (lenta)
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				Vec3b v3 = src.at<Vec3b>(i,j);
				uchar b = v3[0];
				uchar g = v3[1];
				uchar r = v3[2];
				dst.at<uchar>(i,j) = (r+g+b)/3;
			}
		}
		
		//imshow("input image",src);
		//imshow("gray image",dst);
		//waitKey();
		return dst;
}

void testBGR2HSV()
{
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		Mat src = imread(fname);
		int height = src.rows;
		int width = src.cols;

		// Componentele d eculoare ale modelului HSV
		Mat H = Mat(height, width, CV_8UC1);
		Mat S = Mat(height, width, CV_8UC1);
		Mat V = Mat(height, width, CV_8UC1);

		// definire pointeri la matricele (8 biti/pixeli) folosite la afisarea componentelor individuale H,S,V
		uchar* lpH = H.data;
		uchar* lpS = S.data;
		uchar* lpV = V.data;

		Mat hsvImg;
		cvtColor(src, hsvImg, CV_BGR2HSV);

		// definire pointer la matricea (24 biti/pixeli) a imaginii HSV
		uchar* hsvDataPtr = hsvImg.data;

		for (int i = 0; i<height; i++)
		{
			for (int j = 0; j<width; j++)
			{
				int hi = i*width * 3 + j * 3;
				int gi = i*width + j;

				lpH[gi] = hsvDataPtr[hi] * 510 / 360;		// lpH = 0 .. 255
				lpS[gi] = hsvDataPtr[hi + 1];			// lpS = 0 .. 255
				lpV[gi] = hsvDataPtr[hi + 2];			// lpV = 0 .. 255
			}
		}

		imshow("input image", src);
		imshow("H", H);
		imshow("S", S);
		imshow("V", V);

		waitKey();
	}
}

void testResize()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src;
		src = imread(fname);
		Mat dst1,dst2;
		//without interpolation
		resizeImg(src,dst1,320,false);
		//with interpolation
		resizeImg(src,dst2,320,true);
		imshow("input image",src);
		imshow("resized image (without interpolation)",dst1);
		imshow("resized image (with interpolation)",dst2);
		waitKey();
	}
}

void testCanny()
{
	char fname[MAX_PATH];
	while(openFileDlg(fname))
	{
		Mat src,dst,gauss;
		src = imread(fname,CV_LOAD_IMAGE_GRAYSCALE);
		double k = 0.4;
		int pH = 50;
		int pL = (int) k*pH;
		GaussianBlur(src, gauss, Size(5, 5), 0.8, 0.8);
		Canny(gauss,dst,pL,pH,3);
		imshow("input image",src);
		imshow("canny",dst);
		waitKey();
	}
}
Mat convGrayscaleToBW(Mat frame) {
	Mat img = frame;
	Mat dest(img.rows, img.cols, CV_8UC1);

	int src;

	for (int i = 0; i < img.rows; i++)
		for (int j = 0; j < img.cols; j++)
		{
			src = img.at<uchar>(i, j);

			if (src < 160)
				dest.at<uchar>(i, j) = 0;
			else
				dest.at<uchar>(i, j) = 255;
		}

	//imshow("Conversion Grayscale->BW", dest);
	//waitKey(0);
	return dest;
}

Mat Color2Gray(Mat frame)
{

	Mat src = frame;

	int height = src.rows;
	int width = src.cols;

	Mat dst = Mat(height, width, CV_8UC1);

	for (int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			Vec3b v3 = src.at<Vec3b>(i, j);
			uchar b = v3[0];
			uchar g = v3[1];
			uchar r = v3[2];
			dst.at<uchar>(i, j) = (r + g + b) / 3;
		}
	}

	
	return dst;
}
int videoPlay() {

	VideoCapture cap("Videos/video1.avi");

	int frame_width = int(cap.get(CAP_PROP_FRAME_WIDTH));
	int frame_height = int(cap.get(CAP_PROP_FRAME_HEIGHT));

	int imgarea = frame_width * frame_height;

	//aici am creat videoclipul de output
	VideoWriter video("outcpp.avi", CV_FOURCC('X', 'V', 'I', 'D'), 10, Size(frame_width, frame_height));

    
	if (!cap.isOpened()) {
		cout << "Error opening video stream or file" << endl;
		return -1;
	}

	Mat frame1, frame2, diff,gray, threshold1;


	while (cap.isOpened()) {
	
		

		//citesc primele 2 frame-uri
		cap >> frame1;
		cap >> frame2;
		
		//calculez diferenta intre cele 2 farme-uri
		absdiff(frame1, frame2, diff);
		
		//imaginea diferenta o transform gray
		gray= Color2Gray(diff);


		vector<vector<Point>> contur;
		vector<Vec4i> hierarchy;

		//detectezi muchiile folosind treshhold
		threshold(gray, threshold1, 100, 255, THRESH_BINARY);
		//determin conturul
		findContours(threshold1, contur, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));

		// aproximez conturul la cercuri
		vector<vector<Point> > contours_poly(contur.size());

		
		//creez un vector de puncte  pentru fiecare centru de cerc
		vector<Point2f>centru(contur.size());

		//vector cu raza fiecarui cerc
		vector<float>raza(contur.size());
		int i = 0;
		while (i < contur.size())
		{	
			//setez culoarea rosu
			Scalar color = Scalar(0, 0, 255);
			//aproximează o curba cu o alta curba astfel încât distanța dintre ele să fie mai mică sau egală cu precizia specificată
			approxPolyDP(Mat(contur[i]), contours_poly[i], 3, true);

			//gasesc cercurile minime ale setului de puncte
			minEnclosingCircle((Mat)contours_poly[i], centru[i], raza[i]);
		
			//desenez doar cercrile cu raza mai mare ca 900
			if (PI*raza[i] * raza[i] >= 900.0) {
			
			//desenez cercul pe obiecte
			circle(frame1, centru[i], (int)raza[i], color, 2, 8, 0);

				}
			
			imshow("Project", frame1);
			i=i+1;//refac acest lucru pentru fiecare punc din contur
			
		}

		
		video.write(frame1);
		video.write(frame2);//printez cele 2 faramuri

		//opresc programul cand ultimul frame e gol
		if (frame1.empty())
			break;
		
		char c = (char)waitKey(25);
			if (c == 27)
				break;

			
	}

	cap.release();
	destroyAllWindows();
	return 0;

}
Mat regiune(Mat img, const Point* ppt[],int x,int y) {

	//clonez imaginea initiala
	Mat masca=img.clone();

	int lineType = LINE_8;
	int npt[] = { 3 };

	//fac matriea masca alba
	for (int i = 0; i < x; i++) {
		for (int j = 0; j < y; j++) {

			masca.at<uchar>(i, j) = 255;
		}
	
	}
	
	Mat out, out1;
	//unplu regiunea din interiorul triunghiului cu negru
	fillPoly(masca,ppt,npt,1,Scalar(0, 0, 0),lineType,0);
	
	//aplic un and intre masca si imaginea initiala acest lucru imi determina imaginea fara triunghiul negru
	bitwise_and(img, masca, out);
	//aplic un xor intre out si imaginea initiala determinind astfel doar regiunea de imagine care ma intereseaza (triunghiul)
	bitwise_xor(img, out, out1);

	return out1;
}
Mat deseneaza(Mat img , vector<Vec4i> lines) {
	//clonez imagnea in masa si img2
	Mat img2 = img.clone();
	Mat masca = img.clone();

	//parcurg toata imaginea masca si o fac alba
	for (int i = 0; i < img.rows; i++) {
		for (int j = 0; j < img.cols; j++) {

			masca.at<uchar>(i, j) = 0;
		}

	}
	//desenez linia dintre cele 2 puncte ,fac acest lucru pentru toate seturile de puncte returnate de Hough 
	for (int i = 0; i < lines.size(); i++) {
		line(masca, Point(lines[i][0], lines[i][1]), Point(lines[i][2], lines[i][3]), Scalar(0, 0, 255),  5, LINE_8,0);
	}
	Mat out;

	//calculez out=0,8*img2+0.3*masca+0.5 
	addWeighted(img2, 0.8, masca, 0.3, 0.5,out);

	return out;
}
int vdeoPlay2() {
	VideoCapture cap("Videos/line3.avi");
	
	//alfu lungimea si latimea imagini
	int width = int(cap.get(CAP_PROP_FRAME_WIDTH));
	int height = int(cap.get(CAP_PROP_FRAME_HEIGHT));
	
	//declar un triunghi care este  format din trei puncte unul in stanga jos unul dreapta jos si unul aproximativ in mijlocul imagini
	Point points[1][3];
		points[0][0] = Point(200,height);
		points[0][1] = Point(1030, 810 );
		points[0][2] = Point(width-200, height);

	const Point* ppt[1] = { points[0] };
		
	//declar unde vor fi afisate frame-urile
	VideoWriter video("outcpp2.avi", CV_FOURCC('X', 'V', 'I', 'D'), 10, Size(width, height));


	if (!cap.isOpened()) {
		cout << "Error opening video stream or file" << endl;
		return -1;
	}
	Mat frame1, frame2, frame3, gray,canny;

	while (cap.isOpened()) {

		//citesc primul frame
		cap >> frame1;
	
		//aplic pe freame functia regiune care imi determina dimaginea care contine doar pixeli din interiorul triunghiului
		frame2 = regiune(frame1, ppt, height, width);
		//aceasta imaginie a transform gray scale
		gray = Color2Gray(frame2);

		//aflu muchiile din regiune
		Canny(gray, canny,100, 120,  3, false);

		//creez un vector in care fiecare element este un vector cu 4 elemente
		vector<Vec4i> lines;

		//aplic transformata Hough 
		HoughLinesP(canny, lines, 2, PI / 180, 50, 40, 100);

		//deseneaza liniile
		frame3 = deseneaza(frame1,lines);

		//salvez frame-ul modificat
		video.write(frame3);

		if (frame1.empty())
			break;


		char c = (char)waitKey(25);

		if (c == 27)
			break;
	}

	cap.release();
	destroyAllWindows();
	return 0;

}
void testVideoSequence()
{
	VideoCapture cap("Videos/video2.avi"); // off-line video from file
	//VideoCapture cap(0);	// live video from web cam
	if (!cap.isOpened()) {
		printf("Cannot open video capture device.\n");
		waitKey(0);
		return;
	}
		
	Mat edges;
	Mat frame;
	char c;

	while (cap.read(frame))
	{
		Mat grayFrame;
		cvtColor(frame, grayFrame, CV_BGR2GRAY);
		Canny(grayFrame,edges,40,100,3);
		imshow("source", frame);
		imshow("gray", grayFrame);
		imshow("edges", edges);
		c = cvWaitKey(0);  // waits a key press to advance to the next frame
		if (c == 27) {
			// press ESC to exit
			printf("ESC pressed - capture finished\n"); 
			break;  //ESC pressed
		};
	}
}


void testSnap()
{
	VideoCapture cap(0); // open the deafult camera (i.e. the built in web cam)
	if (!cap.isOpened()) // openenig the video device failed
	{
		printf("Cannot open video capture device.\n");
		return;
	}

	Mat frame;
	char numberStr[256];
	char fileName[256];
	
	// video resolution
	Size capS = Size((int)cap.get(CV_CAP_PROP_FRAME_WIDTH),
		(int)cap.get(CV_CAP_PROP_FRAME_HEIGHT));

	// Display window
	const char* WIN_SRC = "Src"; //window for the source frame
	namedWindow(WIN_SRC, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_SRC, 0, 0);

	const char* WIN_DST = "Snapped"; //window for showing the snapped frame
	namedWindow(WIN_DST, CV_WINDOW_AUTOSIZE);
	cvMoveWindow(WIN_DST, capS.width + 10, 0);

	char c;
	int frameNum = -1;
	int frameCount = 0;

	for (;;)
	{
		cap >> frame; // get a new frame from camera
		if (frame.empty())
		{
			printf("End of the video file\n");
			break;
		}

		++frameNum;
		
		imshow(WIN_SRC, frame);

		c = cvWaitKey(10);  // waits a key press to advance to the next frame
		if (c == 27) {
			// press ESC to exit
			printf("ESC pressed - capture finished");
			break;  //ESC pressed
		}
		if (c == 115){ //'s' pressed - snapp the image to a file
			frameCount++;
			fileName[0] = NULL;
			sprintf(numberStr, "%d", frameCount);
			strcat(fileName, "Images/A");
			strcat(fileName, numberStr);
			strcat(fileName, ".bmp");
			bool bSuccess = imwrite(fileName, frame);
			if (!bSuccess) 
			{
				printf("Error writing the snapped image\n");
			}
			else
				imshow(WIN_DST, frame);
		}
	}

}

void MyCallBackFunc(int event, int x, int y, int flags, void* param)
{
	//More examples: http://opencvexamples.blogspot.com/2014/01/detect-mouse-clicks-and-moves-on-image.html
	Mat* src = (Mat*)param;
	if (event == CV_EVENT_LBUTTONDOWN)
		{
			printf("Pos(x,y): %d,%d  Color(RGB): %d,%d,%d\n",
				x, y,
				(int)(*src).at<Vec3b>(y, x)[2],
				(int)(*src).at<Vec3b>(y, x)[1],
				(int)(*src).at<Vec3b>(y, x)[0]);
		}
}

void testMouseClick()
{
	Mat src;
	// Read image from file 
	char fname[MAX_PATH];
	while (openFileDlg(fname))
	{
		src = imread(fname);
		//Create a window
		namedWindow("My Window", 1);

		//set the callback function for any mouse event
		setMouseCallback("My Window", MyCallBackFunc, &src);

		//show the image
		imshow("My Window", src);

		// Wait until user press some key
		waitKey(0);
	}
}

/* Histogram display function - display a histogram using bars (simlilar to L3 / PI)
Input:
name - destination (output) window name
hist - pointer to the vector containing the histogram values
hist_cols - no. of bins (elements) in the histogram = histogram image width
hist_height - height of the histogram image
Call example:
showHistogram ("MyHist", hist_dir, 255, 200);
*/


int main()
{
	int n;
	int op;
	char fname[MAX_PATH];
	Mat img;
	do
	{
		system("cls");
		destroyAllWindows();
		printf("Menu:\n");
		printf(" 1 - Open image\n");
		printf(" 2 - Open BMP images from folder\n");
		printf(" 3 - Image negative - diblook style\n");
		printf(" 4 - BGR->HSV\n");
		printf(" 5 - Resize image\n");
		printf(" 6 - Canny edge detection\n");
		printf(" 7 - Edges in a video sequence\n");
		printf(" 8 - Snap frame from live video\n");
		printf(" 9 - Mouse callback demo\n");
        printf(" 10 -Laborator 5 ex 1 si 2\n");
        printf(" 11 -Laborator 5 ex 3\n");
		printf(" 12 -Laborator 6 ex 1 si 2\n");
		printf(" 13 -Laborator 6 ex 3\n");
		printf(" 14 -Laborator 7 ex1-dilatare\n");
		printf(" 15 -Laborator 7 ex1-erodare\n");
		printf(" 16 -Laborator 7 ex1-deschidere\n");
		printf(" 17 -Laborator 7 ex1-inchidere\n");
		printf(" 18 -Laborator 7 ex3-extragerea_contur\n");
		printf(" 19 -Laborator 7 ex3-umplerea-regiunilor\n");
		printf(" 20 -Proiect\n");
		printf(" 21 -Laborator 8 ex1-mediea nivelurior de intensitate\n");
		printf(" 22 -Laborator 8 ex1.2-deviatia standard\n");
		printf(" 23 -Laborator 10 filtru median \n");
		printf(" 24 -Laborator 10 filtru gasussian\n");
		printf(" 25 -Laborator 8 binarizare golbala \n");
		printf(" 26 -Laborator 8 negativ-imagine \n");
		printf(" 27 -Laborator 8 corectia gama \n");
		printf(" 28 -Laborator 11  Prewitt\n");
		printf(" 29 -Laborator 11  Sobel\n");
		printf(" 30 -Laborator 11  Roberts\n");
		printf(" 31 -Laborator 11  Prewitt-Module\n");
		printf(" 32 -Laborator 11  Sobel-Module\n");
		printf(" 33 -Laborator 11  Roberts-Module\n");
		printf(" 34 -Poiect-line\n");
		printf(" 0 - Exit\n\n");
		printf("Option: ");
		scanf("%d",&op);
		switch (op)
		{
			case 1:
				testOpenImage();
				break;
			case 2:
				testOpenImagesFld();
				break;
			case 3:
				testParcurgereSimplaDiblookStyle(); //diblook style
				break;
			case 4:
				//testColor2Gray();
				testBGR2HSV();
				break;
			case 5:
				testResize();
				break;
			case 6:
				testCanny();
				break;
			case 7:
				testVideoSequence();
				break;
			case 8:
				testSnap();
				break;
			case 9:
				testMouseClick();
				break;
            case 10:
               lab5_ex1si2();
                break;
            case 11:
                lab5_ex3();
                break;
			case 12:
				contur();
				break;
			case 13:
				reconstruct();
				break;
			case 14:
				printf("n= ");
				scanf("%d", &n);
				openFileDlg(fname);
				img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
				imshow("Imagine normala", img);
				imshow("Imagine dilatata", dilatare(img, n));
				waitKey(0);
				break;
			case 15:
				printf("n= ");
				scanf("%d", &n);
				openFileDlg(fname);
				img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
				imshow("Imagine normala", img);
				imshow("Imagine dupa erodare", erodare(img, n));
				waitKey(0);
				break;
			case 16:
				
				openFileDlg(fname);
				img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
				imshow("Imagine normala", img);
				imshow("Deschidere", deschidere(img));
				waitKey(0);
				break;
			case 17:

				openFileDlg(fname);
				img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
				imshow("Imagine normala", img);
				imshow("Inchidere",inchidere(img));
				waitKey(0);
				break;
			case 18:

				//extragere_contur();
				break;
			case 19:

				umplerea_regiunilor();
				break;
			case 20:

				//testVideoSequence();
				videoPlay();
				break;
			case 21:

				media();
				break;
			case 22:

				
				deviatia_standard();
				break;
			case 23:

				openFileDlg(fname);
				img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
				imshow("Imagine normala", img);
				filtruMedian(img,3);
				break;
			case 24:
				openFileDlg(fname);
				img = imread(fname, CV_LOAD_IMAGE_GRAYSCALE);
				imshow("Imagine normala", img);

				filtruGaussian(img,3);
				break;
			case 25:
				

				binarizareGlobala();
				break;
			case 26:


				negativul();
				break;
			case 27:
				corectieG();
				break;
			case 28:
				Prewitt();
				break;
			case 29:
				Sobel();
				break;
			case 30:
				Roberts();
				break;
			case 31:
				PrewittM();
				break;
			
			case 32:
				SobelM();
				break;
			case 33:
				RobertsM();
				break;
			case 34:
				vdeoPlay2();
				break;

		}
	}
	while (op!=0);
	return 0;
}
