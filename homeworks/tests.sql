
update referral set Refferedname = ' 
select * from referral;

delete from rating where Ratingnumber='Two';
update rating set Ratingnumber='Five' where Ratingid=2;
select * from rating;

delete from perk where Perkname= 'free';
update perk set Perkname= 'skydive tickets' where Perkid=2;
select * from perk;

delete from paymethod where Payid=4;
update paymethod set ruser_RUserid=3 where Payid=1;
select * from paymethod;

delete from level where Lname='Competent';
update level set Lname='Extreme' where Levelid=3;
select * from level;

delete from invite where Personname = 'cristiano';
update friends set Personname = 'Neymar' where Inviteid=2;
select * from invite;

delete from friends where Fname= 'Matt';
update friends set Fname='Dave' where Friendid=1; 
select * from friends;

delete from expert where ExpertName='Sam';
update expert set ExpertName='Mike' where Eid=3;
select * from expert;

delete from days where Daysid=3;
update days set Stockid=4 where Daysid=2;
select * from days;

delete from category where Cname='Electronics';
update category set Cname='sports' where Cid=2;
select * from category;

delete from bank where Buydate= 2020-05-23;
update buying set Buydate=2020-05-26 where Stock_Payid=3; 
select * from buying;

delete from bank where BankName= 'Wells Fargo';
update bank set BankName= 'Chase' where Bankid=3;
select * from bank;

delete from account where Accountid=2;
update account set email='nayan@gmail.com' where Accountid=3;
select * from account;

delete from days where Daysid=1;
Update days set stockid=4 where Daysid=2;
select * from days;