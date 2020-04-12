use databasemodel;
delete from account where Accountid=2;
update account set ruser_RUserid=0 where Accountid=2;
select * from account;

delete from bank where Bankid=2;
update bank set BankName= 'Chase' where Bankid=3;
select * from bank;

delete from buying where paymethod_payid= 1;
update buying set Buydate= '2020-05-26' where paymethod_payid=3; 
select * from buying;

delete from category where Cid=1;
update category set Cname='sports' where Cid=2;
select * from category;

delete from days where Daysid=1;
Update days set stockid=4 where Daysid=2;
select * from days;

delete from expert where Eid=1;
update expert set ExpertName='Mike' where Eid=3;
select * from expert;

delete from friends where Friendid=2;
update friends set Fname='Dave' where Friendid=1; 
select * from friends;

delete from invite where Inviteid=3;
update invite set Personname = 'Neymar' where Inviteid=2;
select * from invite;

delete from level where Levelid=2;
update level set Lname='Extreme' where Levelid=3;
select * from level;

delete from maintain where Maintainid=2;
update maintain set ruser_RUserid=2 where Maintainid=1; 
select * from maintain;

delete from paymethod where Payid=4;
update paymethod set ruser_RUserid=3 where Payid=1;
select * from paymethod;

delete from perk where Perkid=1;
update perk set Perkname= 'skydive tickets' where Perkid=2;
select * from perk;

delete from rating where Ratingid=1;
update rating set Ratingnumber='Five' where Ratingid=2;
select * from rating;

delete from referral where Referralid=1;
update referral set Refferedname ='Joshua' where Referralid=2; 
select * from referral;

delete from ruser where Ruserid=3 ;
update ruser set Lastname='Dangi' where RUserid=2;
select * from ruser;

delete from share where Shareid=2;
update share set Stockid=2 where Shareid=1;
select * from share;