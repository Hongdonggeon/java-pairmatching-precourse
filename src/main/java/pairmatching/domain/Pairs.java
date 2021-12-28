package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;

public class Pairs {
	private static Map<Mission, List<Pair>> missionListMap = new HashMap<>();

	public static List<Pair> getPairList(Mission mission) {
		validatePairMatchingExist(mission);
		return missionListMap.get(mission);
	}

	public static void createPair(List<String> crewList, Mission mission) {
		List<String> shuffle = Randoms.shuffle(crewList);
		if (shuffle.size() % 2 == 0) {
			missionListMap.put(mission, createEvenPair(shuffle, mission));
		} else {
			missionListMap.put(mission, createOddPair(shuffle, mission));
		}
	}

	public static List<Pair> createEvenPair(List<String> shuffle, Mission mission) {
		List<Pair> pairList = new ArrayList<>();
		for (int i = 0; i < shuffle.size()-1 ; i+=2) {
			pairList.add(new Pair(mission, shuffle.subList(i, i + 2)));
		}
		return pairList;
	}

	public static List<Pair> createOddPair(List<String> shuffle, Mission mission) {
		List<Pair> pairList = new ArrayList<>();
		for (int i = 0; i < shuffle.size()-4; i+=2) {
			pairList.add(new Pair(mission, shuffle.subList(i, i + 2)));
		}
		pairList.add(new Pair(mission, shuffle.subList(shuffle.size() - 3, shuffle.size())));
		return pairList;
	}

	public static void validatePairMatchingExist(Mission mission) {
		if (!missionListMap.containsKey(mission)) {
			throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
		}
	}

	public static boolean isContainPairing(Mission mission) {
		if (missionListMap.containsKey(mission)) {
			return true;
		}
		return false;
	}

	public static void initializePairs() {
		for (Mission mission : missionListMap.keySet()) {
			missionListMap.get(mission).clear();
		}
	}

	public void duplicatedTest() {

	}
}
