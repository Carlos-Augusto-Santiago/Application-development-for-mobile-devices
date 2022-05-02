// Incluimos librería
#include <DHT.h>
#include <SoftwareSerial.h>  

#define DHTTYPE DHT11

//Capcitancia
#define analogPin      1          
#define chargePin      13         
#define dischargePin   11         
#define resistorValue  10000.0F   
unsigned long startTime;
unsigned long elapsedTime;
float microFarads;                // floating point variable to preserve precision, make calculations
float nanoFarads;


//Tacometro
#define tacometroPin 9

SoftwareSerial miBT(2, 3);  // pin 3 como RX, pin 2 como TX
                                  
int count = 0;
float Tiempo_giro = 0.0;
float RPM;
                                  
// Temperatura
const int DHTPin = 5;     
float h;
float t ;
DHT dht(DHTPin, DHTTYPE);

//Voltaje
float lectura;
float volt;
#define analogVolt 0

String msg;

void setup(){
  pinMode(tacometroPin , INPUT);
  pinMode(analogVolt,INPUT);
  pinMode(chargePin, OUTPUT);     // set chargePin to output
  digitalWrite(chargePin, LOW);
  miBT.begin(9600);    // comunicacion serie entre Arduino y el modulo a 38400 bps
  Serial.begin(9600);             // initialize serial transmission for debugging
  Serial.println("listo");
  dht.begin();
}

void loop(){  
  if (miBT.available() > 0){ 
    msg = miBT.readString(); 
    Serial.println("Android Command: " + msg);
  }  
  Temp();  
  Voltaje();  
  Capacitancia();
  Tacometro();  
  enviarlecturas();
  
  delay(50);
}

void enviarlecturas(){
  String c;
  if (microFarads > 1){
    c = String((long)microFarads) + " microfarads";
  }
  else{
    // if value is smaller than one microFarad, convert to nanoFarads (10^-9 Farad).
    // This is  a workaround because miBT.print will not print floats
    nanoFarads = microFarads * 1000.0;      // multiply by 1000 to convert to nanoFarads (10^-9 Farads)
    c = String((long)microFarads) + " nanoFarads";
  }
   miBT.println(String(h)+ "&" + String(t) + "&" + String(volt) + "&" + String(c) + "&" + String(RPM));
}

//Medir Temperatura
void Temp(){
    // Reading temperature or humidity takes about 250 milliseconds!
    h = dht.readHumidity();
    t = dht.readTemperature();

   if (isnan(h) || isnan(t)) {
      Serial.println("Failed to read from DHT sensor!");
      return;
   }
}

//Medir Voltaje
void Voltaje(){
  lectura = analogRead(analogVolt);
  volt = lectura /1023 * 5.0;
}

//Medir Capacitancia
void Capacitancia(){
  digitalWrite(chargePin, HIGH);  // set chargePin HIGH and capacitor charging
  startTime = millis();
  while(analogRead(analogPin) < 648){       // 647 is 63.2% of 1023, which corresponds to full-scale voltage
  }
  elapsedTime= millis() - startTime;
  microFarads = ((float)elapsedTime / resistorValue) * 1000;
  //Serial.print(elapsedTime);       // print the value to serial port
  //Serial.print(" mS    ");         // print units and carriage return  

  /* dicharge the capacitor  */
  digitalWrite(chargePin, LOW);             // set charge pin to  LOW
  pinMode(dischargePin, OUTPUT);            // set discharge pin to output
  digitalWrite(dischargePin, LOW);          // set discharge pin LOW
  while(analogRead(analogPin) > 0){         // wait until capacitor is completely discharged
  }
  pinMode(dischargePin, INPUT);            // set discharge pin back to input
}

//Medir RPM
void CalcularRPM(){
  Tiempo_giro = millis()- Tiempo_giro;
  RPM = 60000.0 / Tiempo_giro;
  Tiempo_giro = millis();  
}

void Tacometro(){
  if(digitalRead(tacometroPin)!= HIGH){ 
    CalcularRPM();
  }  
}
