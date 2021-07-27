.386
.model flat,stdcall
includelib mscvrt.lib
extern exit:proc
extern fscanf:proc
extern scanf:proc
extern printf:proc
extern fprintf:proc
extern fopen:proc
extern fclose:proc
extern feof:proc
extern sqrt:proc



public start
.data
fisier_A db "Multimea1.txt",0
fisier_B db "Multimea2.txt",0
fisier_rez db "rezultat.txt",0
pointer_A dd 0
pointer_B dd 0
pointer_rez dd 0
mode_read db "r",0
mode_write db "w",0

n_a dd 0
n_b dd 0
x dd 0
ok dd 0
nr dd 0
operatie dd 0
operatie2 dd 0
indice dd 0
indice2 dd 0
aux dd 0

format_intreg db "%d ",0
format_intreg2 db "%d",0
rand db 13,10,0

mesaj1 db "Operatile disponibile sunt:",0
mesaj2 db "1.Verificare unicitate",0
mesaj3 db "2.Apartenenta unui element la multime",0
mesaj4 db "3.Intersectia celor doua multimi",0
mesaj5 db "4.Reuniunea celor doua multimi",0
mesaj6 db "5.Iesire",0
mesaj7 db "Selectati o operatie :",0
mesaj9 db "Multimea 1 NU are toate elementele unice",0
mesaj10 db "Multimea 1  are toate elementele unice",0
mesaj11 db "Doriti sa efectuati o alta operatie? 1)Da      2)NU",0
mesaj12 db "Alegeti multimea pe care doriti sa verificati apartenenta 1)Multimea A     2)Multimea B",0
mesaj13 db "Dati elementul pe care dorti sa il cautati",0
mesaj14 db "Elementul dat apartine multimi",0
mesaj15 db "Elementul dat NU apartine multimi",0
mesaj8 db "Alegeti multimea pe care doriti sa verificati unicitatea 1)Multimea A     2)Multimea B",0
mesaj16 db "Optiune incorecta",0
mesaj17 db "Multimea 2 NU are toate elementele unice",0
mesaj18 db "Multimea 2  are toate elementele unice",0

vector_A dd 10 dup(0)
vector_B dd 10 dup(0)
matrice_rez dd 10 dup (0)

.code
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;; MACROU AFISARE NUMAR ;;;;;;;;;;;;;;;;;;;;;;;;;;;
afisare_intreg macro nr
	push nr
	push offset format_intreg
	call printf
	add esp, 8
endm

;;;;;;;;;;;;; MACROU AFISARE MESAJ ;;;;;;;;;;;;;;;;;;;;;;;;;;;
afisare_mesaj macro msg

	push offset msg
	call printf
	add esp, 4
endm

;;;;;; MACROU AFISARE NUMAR IN FISIERUL rezultat,txt  ;;;;;;;;
afisare_in_fisier macro nr
            push nr
			push offset format_intreg
			push pointer_rez
			call fprintf
			add esp,12
endm

;;;;;;;;;;;;; MACROU AFISARE RAND NOU IN FISIER;;;;;;;;;;;;;;;
rand_nou_in_fisier macro
		push offset rand
		push pointer_rez
		call fprintf
		add esp,8
endm
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;; MACROU AFISARE RAND NOU ;;;;;;;;;;;;;;;;;;;;;;;;
rand_nou macro
		push offset rand 
		call printf
		add esp,4
endm

start:

	;;;; DECHIDERE FISIER MULTIMEA1.TXT ;;;;;;;;;;;;;;;;;;
	
	push offset mode_read
	push offset fisier_A
	call fopen
	add ESP, 8
	mov pointer_A,eax

	;;;; DECHIDERE FISIER MULTIME2.TXT ;;;;;;;;;;;;;;;;;;
	
	push offset mode_read
	push offset fisier_B
	call fopen
	add ESP, 8
	mov pointer_B,eax
	
	;;; DECHIDERE FISIER REZULTAT.TXT ;;;;;;;;;;;;
	push offset mode_write
	push offset fisier_rez
	call fopen
	add ESP, 8
	mov pointer_rez,eax

	
	;;;; CITIRE ORDIN MULTIME 1 ;;;;;
	push offset n_a 
	push offset format_intreg
	push pointer_A
	call fscanf
	add ESP,12
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
	
	;;;; CITIRE ORDIN MULTIME 2 ;;;;;
	push offset n_b 
	push offset format_intreg
	push pointer_B
	call fscanf
	add ESP,12
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	mov indice,0
	mov esi,0
		
	loop1:
		inc indice
		
	
			push offset x 
			push offset format_intreg
			push pointer_A
			call fscanf
			add ESP,12
			mov ebx,x
			
			mov vector_A[esi],ebx
			
			add esi,4
			mov eax,indice
	cmp eax,n_a
	jl loop1
	
   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;     
	mov indice,0
	mov esi,0
		
	loop2:
		inc indice
		
	
			push offset x ;echivalent cu &x din C
			push offset format_intreg
			push pointer_B
			call fscanf
			add ESP,12
			mov ebx,x
			
			mov vector_B[esi],ebx
			
			add esi,4
			mov eax,indice
	cmp eax,n_b
	
	jl loop2
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	                                                          ;Meniu
															  
	afisare_mesaj mesaj1
	rand_nou
	afisare_mesaj mesaj2
	rand_nou
	afisare_mesaj mesaj3
	rand_nou
	afisare_mesaj mesaj4
	rand_nou
	afisare_mesaj mesaj5
	rand_nou
	afisare_mesaj mesaj6
	rand_nou
	
meniu:
	afisare_mesaj mesaj7
	rand_nou
	
	push offset operatie
	push offset format_intreg2
	call scanf
	add esp,8
	
	cmp operatie,1
	je verificare
	
	cmp operatie,2
	je apartenenta
	
	cmp operatie,3
	je intersectie
	
	cmp operatie,4
	je reuniune
	
	cmp operatie,5
	je sfarsit2
	
	afisare_mesaj mesaj16
	rand_nou
	
jmp meniu
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	verificare:
		
		afisare_mesaj mesaj8
		rand_nou
		
		push offset operatie2
		push offset format_intreg2
		call scanf
		add esp,8
		
		mov ok,0
		cmp operatie2,1
		je grup1
		jmp grup2
		grup1:
		mov indice,0
		mov edi,0
		
		loop11:
			inc indice
			
			mov eax,indice
			mov indice2,eax
			add indice2,1
			
			mov esi,edi
			add esi,4
			
			mov eax,vector_A[edi]
			mov aux,eax
			loop12:
				inc indice2
					
					mov eax,vector_A[esi]
					cmp aux,eax
					je bloc10
					jmp here10
					bloc10:
						mov ok,1
						jmp here10
					here10:
					
				add esi,4
				mov eax,indice2
				cmp eax,n_a
			jle loop12
		
			
			add edi,4
			mov eax,indice
			cmp eax,n_a
		jl loop11
		cmp ok,1
			je bloc11
				afisare_mesaj mesaj10
				rand_nou
				jmp here11
				
				bloc11:
					afisare_mesaj mesaj9
					rand_nou
					jmp here11
					
						here11:
							jmp sfarsit
	grup2:
		mov indice,0
		mov edi,0
		
		loop13:
			inc indice
			
			mov eax,indice
			mov indice2,eax
			add indice2,1
			
			mov esi,edi
			add esi,4
			
			mov eax,vector_B[edi]
			mov aux,eax
			loop14:
				inc indice2
					
					mov eax,vector_B[esi]
					cmp aux,eax
					je bloc15
					jmp here14
					bloc15:
						mov ok,1
						jmp here14
				here14:
					
				add esi,4
				mov eax,indice2
				cmp eax,n_b
			jle loop14
		
			
			add edi,4
			mov eax,indice
		cmp eax,n_b
		jl loop13
			cmp ok,1
			je bloc14
			afisare_mesaj mesaj18
			rand_nou
			jmp here15
				
				bloc14:
					afisare_mesaj mesaj17
					rand_nou
					jmp here15
					
							here15:
							jmp sfarsit
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	apartenenta:
		afisare_mesaj mesaj12
		rand_nou
		
		push offset operatie2
		push offset format_intreg2
		call scanf
		add esp,8
		
		cmp operatie2,1
		je bloc2
		jmp bloc5 
		bloc2:
			afisare_mesaj mesaj13
			rand_nou
			
			push offset nr
			push offset format_intreg2
			call scanf
			add esp,8
			
			mov ok,0
			mov indice,0
			mov esi,0
			loop21:
				inc indice
				mov eax,vector_A[esi]
				cmp	nr,eax 
				je bloc3
				jmp here4
					bloc3:
						mov ok,1
						jmp here4
				
			here4:
				add esi,4
				mov eax,indice
				cmp eax,n_a
			jl loop21
			
			cmp ok,1
			je bloc4
				afisare_mesaj mesaj15
				rand_nou
				jmp here5
				
				bloc4:
					afisare_mesaj mesaj14
					rand_nou
					jmp here5
					
						here5:
							jmp sfarsit
				
		bloc5:
		
			afisare_mesaj mesaj13
			rand_nou
			
			push offset nr
			push offset format_intreg2
			call scanf
			add esp,8
			
			mov ok,0
			mov indice,0
			mov esi,0
			loop22:
				inc indice
				mov eax,vector_B[esi]
				cmp	nr,eax 
				je bloc6
				jmp here6
				
					bloc6:
						mov ok,1
						jmp here6
				
				here6:
				
				add esi,4
				mov eax,indice
				cmp eax,n_a
			jl loop22
			
			cmp ok,1
			je bloc7
			
				afisare_mesaj mesaj15
				rand_nou
				jmp here7
			
				bloc7:
					afisare_mesaj mesaj14
					rand_nou
					jmp here7
					
				here7:
	jmp sfarsit	
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	intersectie:
		mov indice,0
		mov edi,0
		
	loop31:
		inc indice
			
			mov indice2,0
			mov esi,0

			loop32:
				inc indice2
		
					mov eax,vector_A[esi]
					mov ebx,vector_B[edi]
						
					cmp eax,ebx
					je bloc1
					jmp here3
					bloc1:
						afisare_in_fisier vector_A[esi]
						jmp here3
					here3:
					
				add esi,4
				mov eax,indice2
				cmp eax,n_a
			jl loop32
	
	
			add edi,4
			mov eax,indice
	cmp eax,n_b
	jl loop31
	jmp sfarsit
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
	reuniune:
		mov indice,0
	mov esi,0
	loop41:
		inc indice
		
			afisare_in_fisier vector_A[esi]
			
			add esi,4
			mov eax,indice
	cmp eax,n_a
	jl loop41
	
	mov indice,0
	mov edi,0
		
	loop42:
		inc indice
			
			mov indice2,0
			mov esi,0
			mov ok,0
			loop43:
				inc indice2
		
					mov eax,vector_A[esi]
					mov ebx,vector_B[edi]
						
					cmp eax,ebx
					je ok1
				 	jmp here1
					ok1:
						mov ok,1
						jmp here1
					
					here1:		
				
				add esi,4
				mov eax,indice2
				cmp eax,n_a
			jl loop43
					
				cmp ok,0
				je ok2
				jmp here2
					ok2:
						afisare_in_fisier vector_B[edi]
						jmp here2
					here2:
					
			mov indice2,0
			mov esi,0
			mov ok,0
			add edi,4
			mov eax,indice
	cmp eax,n_b
	jl loop42
	jmp sfarsit
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	
sfarsit:
	afisare_mesaj mesaj11
	rand_nou
	push offset operatie
	push offset format_intreg2
	call scanf

	rand_nou_in_fisier
 
	cmp operatie,1
	je meniu
 
	sfarsit2:
	
	push pointer_A
	call fclose												
	add ESP, 4												
	
	push pointer_B
	call fclose											
	add ESP, 4		
	
	push pointer_rez
	call fclose												
	add ESP, 4												
	
	push 0
	call exit
end start