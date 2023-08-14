   CREATE TABLE bbs_t(
		b_idx 	int not null PRIMARY KEY AUTO_INCREMENT,
		subject VARCHAR(60),
		writer	VARCHAR(60),
		content longtext,
		f_name VARCHAR(60),
		pwd	  CHAR(60),
		write_date TIMESTAMP,
		hit	   int
     );

   CREATE TABLE comment_t(
		c_idx 	INT not null PRIMARY KEY AUTO_INCREMENT,
		writer	 VARCHAR(20),
		content  longtext,
		write_date TIMESTAMP,
		b_idx	   INT,
	   CONSTRAINT comm_t_fk FOREIGN KEY (b_idx) REFERENCES bbs_t(b_idx)
   );
     