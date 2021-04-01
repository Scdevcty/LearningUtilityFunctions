import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import aiinterface.AIInterface;
import struct.FrameData;
import struct.GameData;
import struct.Key;
import aiinterface.CommandCenter;

public class Defensive implements AIInterface {

    private Key inputKey;
    private boolean playerNumber;
    private FrameData frameData;
    private CommandCenter cc;
    
    private int p1HP;
    private int p2HP;
    private int p1x;
    private int p2x;
    private int p1speedx;
    private int p2speedx;
    private int p1action;
    private int p2action;
	private HashSet<Integer> attackNum;
	private HashSet<Integer> lowAttackNum;
	private HashSet<Integer> mediumAttackNum;	
	private HashSet<Integer> highAttackNum;
	private HashSet<Integer> throwAttackNum;
    
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getInformation(FrameData frameData, boolean arg1) {
		// TODO Auto-generated method stub
        this.frameData = frameData;
        this.cc.setFrameData(this.frameData, this.playerNumber);

	}

	@Override
	public int initialize(GameData gameData, boolean playerNumber) {
		
		// TODO Auto-generated method stub
        this.playerNumber = playerNumber;
        this.inputKey = new Key();
        this.cc = new CommandCenter();
        this.frameData = new FrameData();
        
        
    	this.attackNum=new HashSet<Integer>();
    	this.lowAttackNum= new HashSet<Integer> ();
    	this.mediumAttackNum= new HashSet<Integer> ();	
    	this.highAttackNum= new HashSet<Integer> ();
    	this.throwAttackNum= new HashSet<Integer> ();
        
		addAttackNum(23);addAttackNum(24);
		for(int i=27;i<=55;i++)
			addAttackNum(i);
		addLowAttackNum(29);addLowAttackNum(30);addLowAttackNum(37);addLowAttackNum(38);addLowAttackNum(46);
		addMediumAttackNum(31);addMediumAttackNum(32);addMediumAttackNum(33);addMediumAttackNum(34);addMediumAttackNum(36);addMediumAttackNum(39);addMediumAttackNum(40);addMediumAttackNum(41);addMediumAttackNum(42);addMediumAttackNum(47);addMediumAttackNum(48);addMediumAttackNum(49);addMediumAttackNum(50);addMediumAttackNum(51);addMediumAttackNum(52);addMediumAttackNum(53);addMediumAttackNum(54);
		addHighAttackNum(27);addHighAttackNum(28);addHighAttackNum(35);addHighAttackNum(43);addHighAttackNum(44);addHighAttackNum(45);addHighAttackNum(55);
		addThrowAttackNum(23);addThrowAttackNum(24);
		
		
		return 0;
	}

	@Override
	public Key input() {
		// TODO Auto-generated method stub
		return this.inputKey;
	}

	@Override
	public void processing() {
		// TODO Auto-generated method stub
		 if (this.frameData.getEmptyFlag() || this.frameData.getRemainingFramesNumber() <= 0) {
	            return;
	        }
		 
		 
	        if (this.cc.getSkillFlag()) {
	            this.inputKey = cc.getSkillKey();

	            return;
	        }
	       
	     
	        this.inputKey.empty();
	        this.cc.skillCancel();
	        

	        
	        this.p1HP=frameData.getCharacter(playerNumber).getHp();
	        this.p2HP=frameData.getCharacter(!playerNumber).getHp();
	        this.p1x=frameData.getCharacter(playerNumber).getCenterX();
	        this.p2x=frameData.getCharacter(!playerNumber).getCenterX();
	        this.p1speedx=frameData.getCharacter(playerNumber).getSpeedX();
	        this.p2speedx=frameData.getCharacter(!playerNumber).getSpeedX();
	        //this.p1action=frameData.getCharacter(playerNumber).getAction().ordinal();
	        this.p2action=frameData.getCharacter(!playerNumber).getAction().ordinal();
	        
			//System.out.println("p1action:"+this.p1action);
	        
	        double hp_difference=((double)(this.p1HP-this.p2HP)/400.0+1)/2;
	        double distance=(double)Math.abs(this.p1x-this.p2x)/920.0;
	        double relative_speed=((double)(this.p1speedx-this.p2speedx)/25.0+1)/2;
        	int p2attack=ifAttack(this.p2action);
        	//int p1hitp2=0;
        	
        	int action_num=2;
    		String gene="000101101001100100100100010000100000001000101111111011101101100010101100000001";
    		
    		List<String> actions= getStrList(gene, 39,action_num);
			double score[]=new double[action_num];
			
			for(int i=0;i<action_num;i++)
			{
				/*
				if(i==0)
				{
					this.p1action=2;
					p1hitp2=canHit(this.p1action,this.p2action);
					System.out.println("action0_hit:"+p1hitp2);
				}
				else if(i==1)
				{
					this.p1action=27;
					p1hitp2=canHit(this.p1action,this.p2action);
					System.out.println("action1_hit:"+p1hitp2);
				}
				*/
				score[i]=calscore(actions.get(i),hp_difference,distance,relative_speed,(double)this.p1x,p2attack,(double)this.p2x);
			}
			/*
			System.out.println("action0:"+score[0]);
			System.out.println("action1:"+score[1]);
			*/
			
			
			double max_score=-10000.0;
			int max_index=-1;
			
			for(int i=0;i<action_num;i++)
			{
				if(score[i]>max_score)
				{
					max_score=score[i];
					max_index=i;
				}
			}
	        
			switch(max_index)
			{
			case 0:
				this.cc.commandCall("BACK_STEP");
				break;
			case 1:
				this.cc.commandCall("STAND_GUARD");
				break;
			}
			
			return;
			

	}

	@Override
	public void roundEnd(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public void addAttackNum(int num)
	{
		this.attackNum.add(num);
	}
	public void addLowAttackNum(int num)
	{
		this.lowAttackNum.add(num);
	}
	public void addMediumAttackNum(int num)
	{
		this.mediumAttackNum.add(num);
	}
	public void addHighAttackNum(int num)
	{
		this.highAttackNum.add(num);
	}
	public void addThrowAttackNum(int num)
	{
		this.throwAttackNum.add(num);
	}
	public int ifAttack(int num)
	{
		if(this.attackNum.contains(num))
			return 1;
		else
			return 0;
	}
	public int canHit(int attack, int guard)
	{
		//not attack
		if(ifAttack(attack)==0)
			return 0;
		else
		{
			//STAND_GUARD
			if(guard==10)
			{
				if(this.highAttackNum.contains(attack)||this.mediumAttackNum.contains(attack))
				{
					return 0;
				}
				else
					return 1;
			}
			//CROUCH_GUARD
			if(guard==11)
			{
				if(this.highAttackNum.contains(attack)||this.lowAttackNum.contains(attack))
				{
					return 0;
				}
				else
					return 1;			
			}
			//AIR_GUARD
			if(guard==12)
			{
				if(this.highAttackNum.contains(attack)||this.mediumAttackNum.contains(attack)||this.throwAttackNum.contains(attack))
				{
					return 0;
				}
				else
					return 1;			
			}
			return 1;
		}
	}
    public double calscore(String action, double hp_difference, double distance, double relative_speed, double x_coordinate_player1, double is_attacking,  double x_coordinate_player2 )
    {
    	char actions[]=action.toCharArray();
    	double max=-10000.0,min=10000.0,score=0.0,temp=0.0;
    	List<Double> scores = new ArrayList<Double> ();
    	if(actions[0]=='1')
    	{
    		temp=-1*hp_difference;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[1]=='1')
    	{
    		temp=hp_difference;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[2]=='1')
    	{
    		temp=-1*Math.pow(2.7,hp_difference)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[3]=='1')
    	{
    		temp=Math.pow(2.7,hp_difference)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[4]=='1')
    	{
    		temp=-1*Math.log(hp_difference+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[5]=='1')
    	{
    		temp=Math.log(hp_difference+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[6]=='1')
    	{
    		temp=(-1/(1+Math.pow(2.7,-1*(hp_difference-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[7]=='1')
    	{
    		temp=(1/(1+Math.pow(2.7,-1*(hp_difference-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[8]=='1')
    	{
    		temp=-1*distance;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[9]=='1')
    	{
    		temp=distance;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[10]=='1')
    	{
    		temp=-1*Math.pow(2.7,distance)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[11]=='1')
    	{
    		temp=Math.pow(2.7,distance)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[12]=='1')
    	{
    		temp=-1*Math.log(distance+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[13]=='1')
    	{
    		temp=Math.log(distance+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[14]=='1')
    	{
    		temp=(-1/(1+Math.pow(2.7,-1*(distance-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[15]=='1')
    	{
    		temp=(1/(1+Math.pow(2.7,-1*(distance-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[16]=='1')
    	{
    		temp=-1*relative_speed;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[17]=='1')
    	{
    		temp=relative_speed;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[18]=='1')
    	{
    		temp=-1*Math.pow(2.7,relative_speed)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[19]=='1')
    	{
    		temp=Math.pow(2.7,relative_speed)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[20]=='1')
    	{
    		temp=-1*Math.log(relative_speed+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[21]=='1')
    	{
    		temp=Math.log(relative_speed+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[22]=='1')
    	{
    		temp=(-1/(1+Math.pow(2.7,-1*(relative_speed-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[23]=='1')
    	{
    		temp=(1/(1+Math.pow(2.7,-1*(relative_speed-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[24]=='1')
    	{
    		temp=(2*is_attacking-1)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[25]=='1')
    	{
    		temp=(1-2*is_attacking)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[26]=='1')
    	{
    		
    		if(Math.abs(x_coordinate_player1-x_coordinate_player2)<=245.0)
    			temp=0.5;
    		else
    			temp=-0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    			/*
    		temp=(2*can_hit-1)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    			*/
    			
    	}
    	if(actions[27]=='1')
    	{
    		
    		if(Math.abs(x_coordinate_player1-x_coordinate_player2)<=245.0)
    			temp=-0.5;
    		else
    			temp=0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    		/*
    		temp=(1-2*can_hit)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    		*/
    	}
    	if(actions[28]=='1')
    	{
    		if(x_coordinate_player1>930.0 || x_coordinate_player1<30.0)
    			temp=0.5;
    		else
    			temp=-0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[29]=='1')
    	{
    		if(x_coordinate_player1>930.0 || x_coordinate_player1<30.0)
    			temp=-0.5;
    		else
    			temp=0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[30]=='1')
    	{
    		for(int i=0;i<scores.size();i++)
    		{
    			score+=scores.get(i);
    		}
    	}
    	if(actions[31]=='1')
    	{
    		for(int i=0;i<scores.size();i++)
    		{
    			score+=scores.get(i);
    		}
    		score/=scores.size();
    	}
    	if(actions[32]=='1')
    	{
    		score=max;
    	}
    	if(actions[33]=='1')
    	{
    		score=min;
    	}
    	if(actions[34]=='1')
    	{
    		score=score*0.25;
    	}
    	if(actions[35]=='1')
    	{
    		score=score*0.5;
    	}
    	if(actions[36]=='1')
    	{
    		score=score*1;
    	}
    	if(actions[37]=='1')
    	{
    		score=score*2.0;
    	}
    	if(actions[38]=='1')
    	{
    		score=score*4.0;
    	}
    	return score;
    }
    public String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }
    
    public List<String> getStrList(String inputString, int length,
            int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }
}

