package com.pland;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pland.model.Account;
import com.pland.service.AccountService;

@SpringBootApplication
public class AccessDBTest implements CommandLineRunner {
	
	@Autowired
	private AccountService service = null;
	
	public static void main(String[] args) {
		SpringApplication.run(AccessDBTest.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		this.findAll();
		
//		this.findBySeq(5);
		
//		this.findByMemId("lululee@pland.com");
		
//		this.findAllBySeqs(1,3,5);
		
//		this.findMemSeqs();
		
//		this.insert();
		
//		this.updateBySeq(3);
		
//		this.updatePwdByMemId("lululee@pland.com");
		
//		this.deleteAll();
		
//		this.deleteBySeq(3);
		
//		this.delteByMemId("lululee@pland.com");
		
//		this.exists(3);
		
//		this.count();
		
//		this.randomInsert(250);
		
	}
	
	public void findAll() {
		try {
			List<Account> accounts = this.service.findAll();
			if(accounts!=null && accounts.size()>0) {
				for(Account account : accounts) {
					if(account!=null) {
						account.print(accounts.indexOf(account));
					}
				}
			} else {
				System.out.println("findAll().accounts is null !!");
			}
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("findAll() done !!");
		}
	}
	
	public void findBySeq(int seq) {
		try {
			Account account = ((seq>0)?this.service.findBySeq(seq):null);
			if(account!=null) {
				account.print();
			} else {
				System.out.println("findBySeq("+seq+").account is null !!");
			}
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("findBySeq() done !!");
		}
	}
	
	public void findByMemId(String memId) {
		try {
			Account account = ((StringUtils.isBlank(memId))?null:this.service.findByMemId(memId));
			if(account!=null) {
				account.print();
			} else {
				System.out.println("findByMemId('"+memId+"').account is null !!");
			}
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("findByMemId() done !!");
		}
	}
	
	public void findAllBySeqs(int... seqs) {
		try {
			List<Account> accounts = this.service.findAllBySeqs(seqs);
			if(accounts!=null && accounts.size()>0) {
				for(Account account : accounts) {
					if(account!=null) {
						account.print(accounts.indexOf(account));
					}
				}
			} else {
				System.out.println("findAllBySeqs().accounts is null !!");
			}
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("findAllBySeqs() done !!");
		}
	}
	
	public void findMemSeqs() {
		try {
			List<Integer> seqs = this.service.findMemSeqs();
			if(seqs!=null && seqs.size()>0) {
				for(Integer seq : seqs) {
					if(seq==null) {
						System.out.println("findMemSeqs().seq is null !!");
					} else {
						System.out.println("findMemSeqs().seq == " + seq.intValue());
					}
				}
			} else {
				System.out.println("findMemSeqs().seqs is null !!");
			}
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("findMemSeqs() done !!");
		}
	}
	
	public void insert() {
		try {
			Account account = new Account();
			account.setMemId("rosetzeng@pland.com");
			account.setMemName("Rose Tzeng");
			account.setMemPwd("8542_Ad");
			account.setMemAdmin("Y");
			this.service.insert(account);
			this.findAll();
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("insert() done !!");
		}
	}
	
	public void updateBySeq(int seq) {
		try {
			Account account = ((seq>0)?this.service.findBySeq(seq):null);
			if(account!=null) {
				account.setMemPwd("75a95Pds");
				this.service.update(account);
				this.findAll();
			} else {
				System.out.println("updateBySeq("+seq+").account is null !!");
			}
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("updateBySeq() done !!");
		}
	}
	
	public void updatePwdByMemId(String memId) {
		try {
			if(!StringUtils.isBlank(memId)) {
				this.service.updatePwd("75a95Pds", memId);
			}
			this.findAll();
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("updatePwdByMemId() done !!");
		}
	}
	
	public void deleteAll() {
		try {
			this.service.deleteAll();
			this.findAll();
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("deleteAll() done !!");
		}
	}
	
	public void deleteBySeq(int seq) {
		try {
			if(seq>0) {
				this.service.deleteBySeq(seq);
			}
			this.findAll();
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("deleteBySeq() done !!");
		}
	}
	
	public void delteByMemId(String memId) {
		try {
			if(!StringUtils.isBlank(memId)) {
				this.service.delteByMemId(memId);
			}
			this.findAll();
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("delteByMemId() done !!");
		}
	}
	
	public void exists(int seq) {
		try {
			boolean isExist = this.service.exists(seq);
			System.out.println("exists("+seq+").isExist  == " + isExist);
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("exists() done !!");
		}
	}
	
	public void count() {
		try {
			long count = this.service.count();
			System.out.println("count().count  == " + count);
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("count() done !!");
		}
	}
	
	private int randomNum(int num) {
		try {
			if(num<=0) {
				return -1;
			}
			String numStr = "" + Math.floor(Math.random()*num);
			return Integer.parseInt(numStr.substring(0, numStr.indexOf(".")));
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private boolean isEvenNum() {
		return this.randomNum(100)%2==0;
	}
	
	private Date randomDate(Date baseDate) {
		try {
			if(baseDate==null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				baseDate = sdf.parse("2024/01/01 00:00:00");
			}
			Date date = DateUtils.addDays(baseDate, -this.randomNum(180));
			date = DateUtils.addHours(date, this.randomNum(24)*((this.isEvenNum())?1:-1));
			date = DateUtils.addMinutes(date, this.randomNum(60)*((this.isEvenNum())?1:-1));
			date = DateUtils.addSeconds(date, this.randomNum(60)*((this.isEvenNum())?1:-1));
			return date;
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void randomInsert(int count) {
		int successCount = 0;
		try {
			
			String sNameStr = "Peter,Mary,Carlos,Swing,Mike,"
							+ "Christina,Dragon,Scott,Roger,Rose," 
							+ "David,Bullet,Daniel,Bill,Steven," 
							+ "Michael,Neo,Hades,Cloud,Tifa"
							;
			String eNameStr = "Lee,Lin,Wang,Ping,Chen," 
							+ "Tzeng,Chai,Huang,Hu,Du," 
							+ "Chou,Jiang,Yang,Ho,Chang," 
							+ "Cheng,Wu,Sun,Chao,Kung" 
							;
			String pwdStr = "a,b,c,d,e,f,g,h,i,j,k,l,m,"
						  + "n,o,p,q,r,s,t,u,v,w,x,y,z,"
						  + "0,1,2,3,4,5,6,3,7,8,9,"
						  + "!,_,="
						  ;
			
			String[] sNames = sNameStr.split(",");
			String[] eNames = eNameStr.split(",");
			String[] pwds = pwdStr.split(",");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date baseDate = sdf.parse("2024/01/01 00:00:00");
			
			int insertCount = Math.max(30, this.randomNum(Math.min(sNames.length*eNames.length, count)));
			int retryCount = 5;
			
			List<Account> oriAccounts = this.service.findAll();
			List<String> oriMemIds = new ArrayList<String>();
			if(oriAccounts!=null && oriAccounts.size()>0) {
				for(Account oriAccount : oriAccounts) {
					String oriMemId = ((oriAccount!=null)?oriAccount.getMemId():null);
					if(!StringUtils.isBlank(oriMemId)) {
						oriMemId = oriMemId.trim().toLowerCase().replace("@pland.com", "");
						if(oriMemIds.contains(oriMemId)) {
							continue;
						}
					} else {
						continue;
					}
					oriMemIds.add(oriMemId);
				}
			}
			
			List<String> chkNameList = new ArrayList<String>();
			List<Account> accounts = new ArrayList<Account>();
			for(int i=0; i<insertCount; i++) {
				try {
					int tryCount = 0;
					String sName = null;
					String eName = null;
					do {
						sName = sNames[this.randomNum(sNames.length)];
						eName = eNames[this.randomNum(eNames.length)];
						boolean isEmptySName = StringUtils.isBlank(sName);
						boolean isEmptyEName = StringUtils.isBlank(eName);
						if(isEmptySName || isEmptyEName) {
							tryCount++;
							continue;
						}
						sName = sName.trim().toLowerCase();
						eName = eName.trim().toLowerCase();
						tryCount++;
					} while(chkNameList.contains(sName+eName) && tryCount<retryCount);
					if((tryCount>=retryCount && chkNameList.contains(sName+eName)) 
							|| (oriMemIds!=null && oriMemIds.contains(sName+eName))) {
						continue;
					}
					chkNameList.add(sName+eName);
					Date createDate = null;
					tryCount = 0;
					do {
						createDate = this.randomDate(baseDate);
						tryCount++;
					} while(createDate.after(baseDate) && tryCount<retryCount);
					if(tryCount>=retryCount 
							&& createDate.after(baseDate)) {
						continue;
					}
					Account account = new Account();
					account.setMemId(sName+eName+"@pland.com");
					account.setMemName(StringUtils.capitalize(sName)+" "+StringUtils.capitalize(eName));
					int pwdCount = Math.max(8, this.randomNum(12));
					for(int j=0; j<pwdCount; j++) {
						String pwd = pwds[this.randomNum(pwds.length)];
						account.appendMemPwd(((this.isEvenNum())?pwd.toUpperCase():pwd));
					}
					account.setMemAdmin("N");
					account.setCreateDate(createDate);
					if(this.isEvenNum()) {
						tryCount = 0;
						Date updateDate = null;
						do {
							updateDate = this.randomDate(baseDate);
							tryCount++;
						} while(updateDate.before(createDate) && tryCount<retryCount);
						if(tryCount<retryCount 
								|| (updateDate!=null && updateDate.before(createDate))) {
							account.setUpdateDate(updateDate);
						}
					}
					accounts.add(account);
				} catch(Throwable e) {
					//do nothing
				}
			}
			
			if(accounts!=null && accounts.size()>0) {
				this.service.insertAll(accounts);
				List<Account> newAccounts = this.service.findAll();
				int oriCount = ((oriAccounts!=null)?oriAccounts.size():0);
				int newCount = ((newAccounts!=null)?newAccounts.size():0);
				successCount = newCount - oriCount;
			}
			
		} catch(Throwable e) {
			e.printStackTrace();
		} finally {
			System.out.println("randomInsert("+count+").successCount == " + successCount);
			System.out.println("randomInsert("+count+") done !!");
		}
	}

}
